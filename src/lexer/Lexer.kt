package lexer

import languageConstructs.Keywords
import languageConstructs.Punctuation
import manager.ProjectFileLocation
import utils.matches
import java.lang.Exception
import kotlin.text.StringBuilder

class Lexer {

    companion object {
        val matchKeywordsIdentifierStart = Regex("[a-zA-Z]")
        val matchKeywordsIdentifier = Regex("[a-zA-Z1-9_]")
        val matchNumberStart = Regex("[0-9]")
        val matchNumbers = Regex("[0-9_]")
        val matchUntilNewLine = Regex("[^\\r\\n]")
        val matchNewLine = Regex("[\\r\\n]")
        val matchWhiteSpace = Regex("[\\s]")
        val matchOperator = Regex("[/\\-+=!*<%>&|^?~_]")
        val matchUntilCloseParens = Regex("[^}]")
        const val commentStart = "//"
        const val multiCommentStart = "/*"
        const val multiCommentEnd = "*/"
    }

    private var code = ""
    private lateinit var fileLocation: ProjectFileLocation
    private var charNumber = 0
    private var line = 0
    private var col = 0

    fun lex(fileLocation: ProjectFileLocation, code: String): List<Token> {
        charNumber = 0
        line = 0
        col = 0
        this.code = code
        this.fileLocation = fileLocation

        val tokens: MutableList<Token> = mutableListOf()
        fun prevNonWhiteToken(back: Int = 0): Token? {
            var foundCount = -1
            var reference = 0
            while (reference < tokens.size) {
                val next = tokens[tokens.lastIndex - reference]
                if (next.type != TokenType.WHITE_SPACE) {
                    foundCount++
                }
                reference++
                if (foundCount == back) return next
            }
            return null
        }

        while (!eof()) {
            val possiblePunc = Punctuation.punctuation.filter { it == peekMulti(it.length) }
            tokens.add(when {
                prevNonWhiteToken(1)?.hasProps("raw", TokenType.KEYWORD) ?: false &&
                        prevNonWhiteToken()?.hasProps("{", TokenType.PUNCTUATION) ?: false ->
                    readWhileRegexToToken(matchUntilCloseParens, { TokenType.RAW_CODE })
                prevNonWhiteToken()?.hasProps("\"", TokenType.PUNCTUATION) ?: false
                        && prevNonWhiteToken(1)?.type != TokenType.LITERAL_STRING ->
                    readWhileToToken({ peek() != '"' && !escaped() }, { TokenType.LITERAL_STRING })
                prevNonWhiteToken()?.hasProps("'", TokenType.PUNCTUATION) ?: false
                        && prevNonWhiteToken(1)?.type != TokenType.LITERAL_CHAR ->
                    readWhileToToken({ peek() != '\'' && !escaped() }, { TokenType.LITERAL_CHAR })
                peekMulti(2) == commentStart ->
                    readWhileRegexToToken(matchUntilNewLine, { TokenType.COMMENT })
                peekMulti(2) == multiCommentStart ->
                    readWhileToToken(
                        { peekMulti(2) != multiCommentEnd },
                        { TokenType.COMMENT },
                        { popMulti(2) }
                    )
                possiblePunc.isNotEmpty() ->
                    tokenFromNext(possiblePunc.maxOrNull()!!.length, TokenType.PUNCTUATION)
                peek().matches(matchKeywordsIdentifierStart) ->
                    readWhileRegexToToken(
                        matchKeywordsIdentifier,
                        { if (it in Keywords.keywords) TokenType.KEYWORD else TokenType.IDENTIFIER }
                    )
                peek().matches(matchOperator) ->
                    readWhileRegexToToken(matchOperator, { TokenType.OPERATOR })
                peek().matches(matchNumberStart) -> {
                    tokenFromString(TokenType.LITERAL_NUM) {
                        readWhileRegex(matchNumbers) + if (peek() == '.') pop() + readWhileRegex(matchNumbers) else ""
                    }
                }
                //peek().matches(matchNewLine) ->
                //    readWhileRegexToToken(matchNewLine, { TokenType.NEW_LINE })
                peek().matches(matchWhiteSpace) ->
                    readWhileRegexToToken(matchWhiteSpace, { TokenType.WHITE_SPACE })
                else -> throw Exception("Unable to lex ${peek()} at ${getCodePosition()}")
            }
            )
        }

        return tokens.toList()
    }

    private fun escaped(): Boolean = prev() == '\\'

    private fun tokenFromString(type: TokenType, text: () -> String): Token {
        val startPosition = getCodePosition()
        return Token(text(), type, startPosition)
    }

    private fun tokenFromNext(chars: Int, type: TokenType): Token {
        val startPosition = getCodePosition()
        return Token(popMulti(chars), type, startPosition)
    }

    private fun readWhileRegexToToken(regex: Regex, type: (String) -> TokenType, final: () -> String = { "" }): Token =
        readWhileToToken({ peek().matches(regex) }, type, final)

    private fun readWhileToToken(
        test: () -> Boolean,
        type: (String) -> TokenType,
        final: () -> String = { "" }
    ): Token {
        val startPosition = getCodePosition()
        val string = readWhile(test, final)
        return Token(string, type(string), startPosition)
    }

    private fun readWhileRegex(regex: Regex, final: () -> String = { "" }): String =
        readWhile({ peek().matches(regex) }, final)

    private fun readWhile(test: () -> Boolean, final: () -> String = { "" }): String {
        val string = StringBuilder()
        while (!eof() && test()) {
            string.append(pop())
        }
        string.append(final())
        return string.toString()
    }

    private fun getCodePosition(): CodePosition = CodePosition(fileLocation, line, col)

    private fun eof(): Boolean = charNumber >= code.length

    private fun peekMulti(chars: Int): String? =
        if (charNumber + chars >= code.length) null
        else code.subSequence(charNumber, charNumber + chars).toString()

    private fun peek(): Char = code[charNumber]

    private fun prev(back: Int = 1): Char? = if (charNumber < back) null else code[charNumber - back]

    private fun popMulti(chars: Int): String = (1..chars).fold("") { acc, _ -> acc + pop() }

    private fun pop(): Char {
        val ret = code[charNumber]
        charNumber++
        if (ret == '\n') {
            line++
            col = 0
        } else {
            col++
        }
        return ret
    }
}