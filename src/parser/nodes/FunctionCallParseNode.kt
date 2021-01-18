package parser.nodes

import manager.CodeLocation

/**
 * Represents a parse node that could be referring to a function.
 */
interface FunctionReferenceParseNode

/**
 * Represents a call to a function whether explicitly declared or as part of an expression.
 */
class FunctionCallParseNode(
    val reference: FunctionReferenceParseNode,
    val arguments: List<ExpressionParseNode>,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation), ExpressionParseNode