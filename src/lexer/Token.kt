package lexer

data class Token(val text: String, val type: TokenType) {
    var codePosition: CodePosition? = null

    constructor(text: String, type: TokenType, codePosition: CodePosition): this(text, type) {
        this.codePosition = codePosition
    }

    fun hasProps(text: String, type: TokenType): Boolean = this.text == text && this.type == type
}