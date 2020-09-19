package parser.nodes

import lexer.CodePosition
import parser.types.FunctionType
import parser.types.Type

class FunctionDeclarationPN(
    val name: String,
    val args: List<FunctionArg>,
    val type: FunctionType,
    val code: CodeBlockPN,
    codePosition: CodePosition
) : ParseNode(codePosition) {
}

data class FunctionArg(val name: String, val callString: String?, val type: Type, val codePosition: CodePosition)