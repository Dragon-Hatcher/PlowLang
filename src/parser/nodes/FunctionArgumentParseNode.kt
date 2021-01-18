package parser.nodes

import manager.CodeLocation

/**
 * A parameter to a function or method.
 *
 * @property name the name of the function parameter.
 * @property type the type of the function parameter.
 */
class FunctionArgumentParseNode(
    val name: IdentifierParseNode,
    val type: TypeParseNode,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)
