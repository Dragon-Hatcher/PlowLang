package parser.nodes.objects

import manager.CodeLocation
import parser.nodes.*

/**
 * A parse node representing a class, struct, or enum but not a protocol.
 *
 * @property modifiers a list of any modifiers on the object.
 * @property typeConstructor the type constructor for the object.
 * @property methods the list of methods belonging to the object. This is **not** the list of functions namespaced by the object.
 * @property functions the list of functions namespaced by the object. This is **not** the list of methods owned by the object.
 * @property fields the list of fields belonging to the object. This is **not the list of variables namespaced by the object.
 * @property variables the list of variables namespaced by the object. This is **not the list of fields owned by the object.
 */
abstract class ObjectParseNode(
    val modifiers: List<ModifierParseNode>,
    val typeConstructor: TypeConstructorParseNode,
    val methods: List<MethodParseNode>,
    val functions: List<FunctionParseNode>,
    val fields: List<FieldParseNode>,
    val variables: List<VariableParseNode>,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)
