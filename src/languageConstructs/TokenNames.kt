package languageConstructs

class Keywords {
    companion object {
        val keywords = listOf("var", "if", "else", "while", "break", "true", "false", "nil",
            "as", "main", "func", "return", "void", "raw", "class", "struct", "enum", "protocol",
            "public", "private", "protected", "static", "import")
    }
}

class Punctuation {
    companion object {
        val punctuation = listOf("{", "}", "(", ")", "[", "]", ",", ".", ";", ":", "::", "->", "\"", "\'", "_")
    }
}