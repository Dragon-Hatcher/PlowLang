package parser.nodes

import manager.CodeLocation

/**
 * Represents a `break` statement.
 */
class BreakParseNode(codeLocation: CodeLocation): BaseParseNode(codeLocation), StatementParseNode

/**
 * Represents a `continue` statement.
 */
class ContinueParseNode(codeLocation: CodeLocation): BaseParseNode(codeLocation), StatementParseNode

/**
 * Represents a `return` statement. [value] is null if the return statement doesn't return anything.
 */
class ReturnParseNode(
    val value: ExpressionParseNode?,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation), StatementParseNode