package parser.nodes

import manager.CodeLocation

/**
 * Represents a variable such as in an object, or in local or global scope.
 */
open class VariableParseNode(
    val modifiers: List<ModifierParseNode>,
    val name: IdentifierParseNode,
    val type: TypeParseNode,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation), StatementParseNode
