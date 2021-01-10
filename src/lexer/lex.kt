package lexer

import extensions.matches
import extensions.normalizeNewLines
import manager.CodeLocation
import manager.FileLocation
import manager.ProjectFile
import manager.TextLocation


class Lexer(codeIn: String, private val fileLocation: FileLocation) {

    companion object {
        val matchKeywordsIdentifierStart = "[a-zA-Z]".toRegex()
        val matchKeywordsIdentifier = "[a-zA-Z1-9_]".toRegex()
        val matchNumberStart = "[0-9]".toRegex()
        val matchNumbers = "[0-9_]".toRegex()
        val matchUntilNewLine = "[^\\r\\n]".toRegex()
        val matchNewLine = "[\\r\\n]".toRegex()
        val matchWhiteSpace = "[\\s]".toRegex()
        val matchOperator = "[/\\-+=!*<%>&|^?~_]".toRegex()
        val matchUntilCloseParens = "[^}]".toRegex()
        const val commentStart = "//"
        const val multiCommentStart = "/*"
        const val multiCommentEnd = "*/"
    }

    constructor(projectFile: ProjectFile) : this(projectFile.text, projectFile.fileLocation)

    private val code = codeIn.normalizeNewLines()
    private val tokens = mutableListOf<LexToken>()
    private var position = 0
    private var line = 0
    private var col = 0

    fun getTokens(): List<LexToken> {
        if (tokens.size != 0) return tokens

        while (!eof()) {
            when {
                peek() == '\"' || peek() == '\'' -> {
                    //TODO again problems if this isn't closed
                    val opener = pop()
                    val type = if(opener == '\"') LexTokenType.LITERAL_STRING else LexTokenType.LITERAL_CHAR
                    tokens.add(assembleLexToken(opener.toString(), LexTokenType.PUNCTUATION))
                    val text = readWhile { peek() != opener || escaped() }
                    tokens.add(assembleLexToken(text, type))
                    tokens.add(assembleLexToken(pop().toString(), LexTokenType.PUNCTUATION))
                }
                punctuation.any { it == peekMulti(it.length) } -> {
                    val foundPunctuation = punctuation.filter { it == peekMulti(it.length) }.maxByOrNull { it.length }!!
                    popMulti(foundPunctuation.length)
                    tokens.add(assembleLexToken(foundPunctuation, LexTokenType.PUNCTUATION))
                }
                peek().matches(matchKeywordsIdentifierStart) -> {
                    val identifier = pop() + readWhile { peek().matches(matchKeywordsIdentifier) }
                    val type = if (identifier in keywords) LexTokenType.KEYWORD else LexTokenType.IDENTIFIER
                    tokens.add(assembleLexToken(identifier, type))
                }
                peek().matches(matchOperator) -> {
                    val operator = readWhile { peek().matches(matchOperator) }
                    tokens.add(assembleLexToken(operator, LexTokenType.OPERATOR))
                }
                peek().matches(matchNumberStart) -> {
                    var numberStart = readWhile { peek().matches(matchNumbers) }
                    if (peek() == '.') {
                        numberStart += pop() + readWhile { peek().matches(matchNumbers) }
                    }
                    tokens.add(assembleLexToken(numberStart, LexTokenType.LITERAL_NUM))
                }
                peekMulti(commentStart.length) == commentStart -> {
                    //TODO what happens if there is no newline
                    val comment = readWhile { peek().matches(matchUntilNewLine) }
                    tokens.add(assembleLexToken(comment, LexTokenType.COMMENT))
                }
                peekMulti(multiCommentStart.length) == commentStart -> {
                    //TODO this will crash if the comment is unclosed
                    val comment = readWhile { peekMulti(multiCommentEnd.length) == multiCommentEnd }
                    tokens.add(assembleLexToken(comment, LexTokenType.COMMENT))
                }
                peek().matches(matchWhiteSpace) -> {
                    val whiteSpace = readWhile { peek().matches(matchWhiteSpace) }
                    tokens.add(assembleLexToken(whiteSpace, LexTokenType.WHITE_SPACE))
                }
            }
        }

        return tokens
    }

    private fun assembleLexToken(text: String, type: LexTokenType) =
        LexToken(text, type, CodeLocation(fileLocation, TextLocation(line, col)))

    private fun readWhile(endBehavior: () -> Unit = {}, test: () -> Boolean): String {
        var result = ""
        while (!eof() && test()) result += pop()
        if(eof()) endBehavior()
        return result
    }

    private fun escaped(): Boolean = prev() == '\\'
    private fun eof() = position >= code.length

    //TODO this should probably return null if there is no char
    private fun peek() = code[position]
    private fun peekMulti(chars: Int): String? =
        if (position + chars >= code.length) null
        else code.subSequence(position, position + chars).toString()

    private fun prev(back: Int = 1): Char? = if (position - back !in code.indices) null else code[position - back]
    private fun pop(): Char {
        val c = peek()
        position++
        if (!eof() && code[position] == '\n') {
            line++
            col = 0
        } else {
            col++
        }
        return c
    }

    private fun popMulti(chars: Int): String = (1..chars).map { pop() }.joinToString(separator = "")

}
