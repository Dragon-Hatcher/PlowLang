package parser.nodes

import manager.CodeLocation

/**
 * Represents any identifier.
 */
class IdentifierParseNode(
    val name: String,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)