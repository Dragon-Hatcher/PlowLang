package parser.nodes

import manager.CodeLocation

/**
 * Represents a `while` loop. In reality the condition must be of type `Bool` but that is not checked here.
 */
class WhileParseNode(
    val condition: ExpressionParseNode,
    val body: CodeBlockParseNode,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation), StatementParseNode