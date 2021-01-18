package parser.nodes

import manager.CodeLocation

/**
 * Represents a type constructor. This is the type of an object such as class, struct, or protocol. This is **not** the
 * type of a variable. For that use [TypeParseNode].
 *
 * @property name the name of the object without any type parameters. (ex. `Map`).
 * @property typeParameters the list of type parameters the object has. (ex. `<Key, Value>`). If the object has no type
 * parameters this will be empty
 */
class TypeConstructorParseNode(
    val name: String,
    val typeParameters: List<TypeParameter>,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation)

/**
 * A type parameter of an object. (ex. the `T` in `List<T>`). Used by [TypeConstructorParseNode].
 *
 * @property name the placeholder name of the type to be used throughout the object.
 * @property constraints the list of constraints the property must abide by.
 */
class TypeParameter(
    val name: String,
    val constraints: List<TypeParameterConstraint>
)

/**
 * A rule that a type parameter of a class, struct, enum, or protocol must follow.
 */
abstract class TypeParameterConstraint

/**
 * Requires that the type parameter that it is applied to is a subtype of the given type.
 */
class SubtypeOfTypeParameterConstraint: TypeParameterConstraint()