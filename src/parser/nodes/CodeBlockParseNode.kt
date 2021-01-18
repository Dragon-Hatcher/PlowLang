package parser.nodes

import manager.CodeLocation

/**
 * Represents a sequence of [StatementParseNode]s.
 */
class CodeBlockParseNode(
    val statement: List<StatementParseNode>,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)