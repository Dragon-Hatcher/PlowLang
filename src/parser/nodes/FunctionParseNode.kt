package parser.nodes

import manager.CodeLocation

/**
 * A parse node that represents a function.
 *
 * @property modifiers any modifiers on the function
 * @property typeConstructor the [TypeConstructorParseNode] for the function.
 * @property arguments a list of all the [FunctionArgumentParseNode]s of the function. Empty if the functions has no arguments.
 * @property returnType the return type of the function. `Unit` if the function doesn't return anything.
 */
open class FunctionParseNode(
    val modifiers: List<ModifierParseNode>,
    val typeConstructor: TypeConstructorParseNode,
    val arguments: List<FunctionArgumentParseNode>,
    val returnType: TypeParseNode,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)

