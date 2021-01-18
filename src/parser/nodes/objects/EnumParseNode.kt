package parser.nodes.objects

import manager.CodeLocation
import parser.nodes.*

typealias EnumCase = IdentifierParseNode

/**
 * A parse node representing a enumeration. An enum does not have any fields.
 */
class EnumParseNode(
    modifiers: List<ModifierParseNode>,
    typeConstructor: TypeConstructorParseNode,
    val cases: List<EnumCase>,
    methods: List<MethodParseNode>,
    functions: List<FunctionParseNode>,
    variables: List<VariableParseNode>,
    codeLocation: CodeLocation
): ObjectParseNode(modifiers, typeConstructor, methods, functions, listOf(), variables, codeLocation)