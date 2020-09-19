package parser.nodes

import lexer.CodePosition

abstract class ParseNode(val codePosition: CodePosition) {

    fun indented(s: String): String =
        s.split("\n").joinToString(separator = "\n") { "  $it" }

}