package parser.nodes

import manager.CodeLocation

enum class ModifierType {
    PUBLIC,
    PRIVATE,
    PROTECTED,
    STATIC,
    MUTATING
}

/**
 * A modifier on an object, function, or variable. One of the [ModifierType]s.
 */
class ModifierParseNode(
    val type: ModifierType,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)