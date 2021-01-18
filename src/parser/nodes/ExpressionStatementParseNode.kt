package parser.nodes

import manager.CodeLocation

/**
 * A statement that contains only a single expression. This allows code blocks such as `i++` to be allowed at the top level
 * of code.
 */
class ExpressionStatementParseNode(
    val expression: ExpressionParseNode,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation), StatementParseNode