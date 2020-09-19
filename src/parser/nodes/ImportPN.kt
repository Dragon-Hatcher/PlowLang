package parser.nodes

import lexer.CodePosition

class ImportPN(val fileToImport: String, val asWhat: String?, codePosition: CodePosition): ParseNode(codePosition) {

    override fun toString(): String {
        return "(import $fileToImport" + if(asWhat != null) " as $asWhat)" else ")"
    }
}