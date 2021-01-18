package parser.nodes

import manager.CodeLocation

/**
 * Represents a type constructor. This is the type of an object such as class, struct, or protocol or a function. This is
 * **not** the type of a variable. For that use [TypeParseNode].
 *
 * @property name the name of the object without any type parameters. (ex. `Map`).
 * @property typeParameters the list of type parameters the object has. (ex. `<Key, Value>`). If the object has no type
 * parameters this will be empty.
 */
class TypeConstructorParseNode(
    val name: IdentifierParseNode,
    val typeParameters: List<TypeParameter>,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)

/**
 * A type parameter of an object. (ex. the `T` in `List<T>`). Used by [TypeConstructorParseNode].
 *
 * @property name the placeholder name of the type to be used throughout the object.
 * @property constraints the list of types the property must be a subclass of.
 */
class TypeParameter(
    val name: IdentifierParseNode,
    val constraints: List<TypeParseNode>,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)
