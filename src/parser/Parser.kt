package parser

import lexer.CodePosition
import lexer.Token
import lexer.TokenType
import manager.ProjectFileLocation
import parser.nodes.*
import parser.types.Type
import parser.types.TypeLocation
import java.lang.Exception

class Parser(val fileLocations: Set<ProjectFileLocation>) {

    private var tokens: List<Token> = ArrayList()

    private val modifierKeywords = listOf("public", "private", "protected", "static").map { Token(it, TokenType.KEYWORD) }

    private var tokenNum = 0

    fun parse(tokensIn: List<Token>): FilePN {
        tokens = tokensIn.filter { it.type != TokenType.WHITE_SPACE && it.type != TokenType.COMMENT }
        tokenNum = 0

        val fileNodes = ArrayList<ParseNode>()
        val codePosition = getCodePosition()
        var accessModifier: String? = null

        while (isKw("import")) fileNodes.add(parseImport())

        while (!eof()) {
            when {
                modifierKeywords.contains(peek()) ->
                    if (accessModifier == null) accessModifier = pop().text
                    else throw ConsecutiveScopeDeclarationsException("Consecutive scope modifiers ${accessModifier!!} and ${peek().text} ${peek().codePosition}.")
//                isKw("func") ->
//                    fileNodes.add(parseFunction(accessModifier)); accessModifier = null
                else -> throw Exception("Don't know how to parse ${peek()} ${peek().codePosition}.")

            }
        }

        return FilePN(fileNodes, codePosition)
    }

    //region parse functions
    private fun parseImport(): ImportPN {
        val position = getCodePosition()
        eatToken("import", TokenType.KEYWORD)
        val fileToImport = eatType(TokenType.IDENTIFIER)
        var asWhat: String? = null
        if (isKw("as")) {
            eatToken("as", TokenType.KEYWORD)
            asWhat = eatType(TokenType.IDENTIFIER)
        }
        eatToken(";", TokenType.PUNCTUATION)
        return ImportPN(fileToImport, asWhat, position)
    }

    private fun parseFunction(accessLevelIn: String?): FunctionDeclarationPN {
        val codePosition = getCodePosition()
        val accessLevel = accessLevelIn ?: "public"
        eatToken("func", TokenType.KEYWORD)
        val functionName = eatType(TokenType.IDENTIFIER)
        val functionArgs = parseArgumentList()
    }

    private fun parseArgumentList(): List<FunctionArg> {
        val args = ArrayList<FunctionArg>()
        eatToken("(", TokenType.PUNCTUATION)
        do {
            val codePosition = getCodePosition()
            var firstIdentifier: String? = null
            if (!maybeEat("_", TokenType.PUNCTUATION)) {
                firstIdentifier = eatType(TokenType.IDENTIFIER)
            }
            var secondIdentifier: String? = null
            if (isType(TokenType.IDENTIFIER)) secondIdentifier = eatType(TokenType.IDENTIFIER)
            if(firstIdentifier == null && secondIdentifier == null) {
                throw UndefinedArgumentNameException("The function argument $codePosition must have a name after the _.")
            }
            eatToken(":", TokenType.PUNCTUATION)
            val type = parseType()
            args.add(when{
                firstIdentifier == null -> FunctionArg(secondIdentifier!!, null, type, codePosition)
                secondIdentifier == null -> FunctionArg(firstIdentifier, firstIdentifier, type, codePosition)
                else -> FunctionArg(secondIdentifier, firstIdentifier, type, codePosition)
            })
        } while (maybeEat(",", TokenType.PUNCTUATION))
        eatToken(")", TokenType.PUNCTUATION)
        return args
    }

    private fun parseType(): Type {
        val typeLocation = parseLibModDeclaration()
    }

    private fun parseLibModDeclaration(): TypeLocation? {

    }

    private fun parseGenericList(): List<Type> {
        if(isType(TokenType.IDENTIFIER) && peekS(1)?.hasProps("::", TokenType.PUNCTUATION) == true) {
            val firstId = pop().text
            var secondId: String? = null
            pop()
            if(isType(TokenType.IDENTIFIER) && peekS(1)?.hasProps("::", TokenType.PUNCTUATION) == true) {
                secondId = pop().text
                pop()
            }
        }
    }


    //endregion


    //region Utility functions
    private fun getCodePosition(): CodePosition = peek().codePosition!!

    private fun eof(): Boolean = tokenNum >= tokens.size

    private fun peekS(ahead: Int = 0): Token? = if (tokenNum + ahead < tokens.size) tokens[tokenNum + ahead] else null
    private fun peek(): Token = tokens[tokenNum]

    private fun isKw(str: String): Boolean = peekHasProps(str, TokenType.KEYWORD)
    private fun isPunc(str: String): Boolean = peekHasProps(str, TokenType.PUNCTUATION)
    private fun peekHasProps(str: String, type: TokenType): Boolean = peekS()?.hasProps(str, type) ?: false

    private fun isType(type: TokenType): Boolean = peekS()?.type == type

    private fun maybeEat(str: String, type: TokenType): Boolean =
        if (peekHasProps(str, type)) {
            pop()
            true
        } else {
            false
        }

    private fun eatToken(text: String, type: TokenType) {
        val p = pop()
        if (!p.hasProps(text, type)) {
            throw UnexpectedTokenException("Was expecting ${Token(text, type)} but instead found $p ${p.codePosition}.")
        }
    }

    private fun eatType(type: TokenType): String {
        val p = pop()
        if (p.type != type) {
            throw UnexpectedTokenException("Was expecting token of type $type but instead found $p ${p.codePosition}.")
        }
        return p.text
    }

    private fun prev(back: Int = 1): Token? = if (tokenNum < back) null else tokens[tokenNum - back]

    private fun pop(): Token = tokens[tokenNum++]
    //endregion
}
