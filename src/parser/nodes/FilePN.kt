package parser.nodes

import lexer.CodePosition

class FilePN(val nodes: List<ParseNode>, codePosition: CodePosition): ParseNode(codePosition) {
    override fun toString(): String {
        return "\n(File:\n" + nodes.joinToString(separator = "") { indented(it.toString()) } + "\n)\n"
    }
}