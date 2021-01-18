package lexer

import manager.CodeLocation

/**
 * All the types of [LexToken]s.
 */
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

/**
 * A token generated from a source file.
 *
 * @property text the source code that was used to generate the token.
 * @property type the type of the [LexToken].
 * @property location the [CodeLocation] of the first character of the [text] property.
 */
data class LexToken(val text: String, val type: LexTokenType, val location: CodeLocation) {
    fun minimumString() = "text= ${text.replace("\n", "\\n")} type=$type"
}