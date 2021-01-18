package parser.nodes.objects

import manager.CodeLocation
import parser.nodes.FunctionParseNode
import parser.nodes.ModifierParseNode
import parser.nodes.TypeConstructorParseNode
import parser.nodes.VariableParseNode

/**
 * A parse node representing a structure.
 */
class StructParseNode(
    modifiers: List<ModifierParseNode>,
    typeConstructor: TypeConstructorParseNode,
    methods: List<MethodParseNode>,
    functions: List<FunctionParseNode>,
    fields: List<FieldParseNode>,
    variables: List<VariableParseNode>,
    codeLocation: CodeLocation
): ObjectParseNode(modifiers, typeConstructor, methods, functions, fields, variables, codeLocation)
