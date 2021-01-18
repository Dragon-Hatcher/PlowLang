package parser.nodes.objects

import manager.CodeLocation
import parser.nodes.*

/**
 * A method of an object. This is different from a function as it belongs to the object and is not just namespaced by the object.
 */
class MethodParseNode(
    modifiers: List<ModifierParseNode>,
    typeConstructor: TypeConstructorParseNode,
    arguments: List<FunctionArgumentParseNode>,
    returnType: TypeParseNode,
    codeLocation: CodeLocation
): FunctionParseNode(modifiers, typeConstructor, arguments, returnType, codeLocation)