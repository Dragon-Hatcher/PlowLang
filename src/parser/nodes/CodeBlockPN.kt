package parser.nodes

import lexer.CodePosition

class CodeBlockPN(val parseNodes: List<ParseNode>, codePosition: CodePosition): ParseNode(codePosition) {
}