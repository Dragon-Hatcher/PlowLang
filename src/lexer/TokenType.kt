package lexer

enum class TokenType {
    COMMENT,
    WHITE_SPACE,
    KEYWORD,
    IDENTIFIER,
    PUNCTUATION,
    OPERATOR,
    LITERAL_NUM,
    LITERAL_STRING,
    LITERAL_CHAR,
    RAW_CODE
}