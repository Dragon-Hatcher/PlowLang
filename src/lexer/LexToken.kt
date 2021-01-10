package lexer

import manager.CodeLocation

enum class LexTokenType {
    COMMENT,
    WHITE_SPACE,
    KEYWORD,
    IDENTIFIER,
    PUNCTUATION,
    OPERATOR,
    LITERAL_NUM,
    LITERAL_STRING,
    LITERAL_CHAR,
}

data class LexToken(val text: String, val type: LexTokenType, val location: CodeLocation) {
    fun minimumString() = "text= ${text.replace("\n", "\\n")} type=$type"
}