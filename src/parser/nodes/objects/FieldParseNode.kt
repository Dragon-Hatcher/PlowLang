package parser.nodes.objects

import manager.CodeLocation
import parser.nodes.IdentifierParseNode
import parser.nodes.ModifierParseNode
import parser.nodes.TypeParseNode
import parser.nodes.VariableParseNode

/**
 * Represents a field in an object.
 */
class FieldParseNode(
    modifiers: List<ModifierParseNode>,
    name: IdentifierParseNode,
    type: TypeParseNode,
    codeLocation: CodeLocation
): VariableParseNode(modifiers, name, type, codeLocation)