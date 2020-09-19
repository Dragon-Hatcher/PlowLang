package languageConstructs

class Keywords {
    companion object {
        val keywords = listOf("var", "if", "else", "while", "break", "true", "false", "nil",
            "main", "func", "return", "void", "raw", "class", "struct", "protocol",
            "public", "private", "protected", "static", "import", "as")
    }
}

class Punctuation {
    companion object {
        val punctuation = listOf("{", "}", "(", ")", "[", "]", ",", ".", ";", ":", "::", "->", "\"", "\'", "_")
    }
}