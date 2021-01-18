package parser.nodes

import manager.CodeLocation

/**
 * Represents an `if` statement. In reality the condition must be of type `Bool` but that is not checked here. This class
 * cannot have an else path. For an else path use [IfElseParseNode]. This structure is used because an if with an else
 * path is an expression but one without is not.
 */
open class IfParseNode(
    val condition: ExpressionParseNode,
    val truePath: CodeBlockParseNode,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation), StatementParseNode

/**
 * Represents an `if` `else` statement. In reality the condition must be of type `Bool` but that is not checked here.
 * This class must have an else path. For an if without an else path use [IfParseNode]. This structure is used because
 * an if with an else path is an expression but one without is not.
 */
class IfElseParseNode(
    condition: ExpressionParseNode,
    truePath: CodeBlockParseNode,
    val falsePath: CodeBlockParseNode,
    codeLocation: CodeLocation
): IfParseNode(condition, truePath, codeLocation), ExpressionParseNode