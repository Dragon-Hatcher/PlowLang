package parser.nodes

import manager.*

/**
 * Represents a type of a variable, **not** the type of a class, struct, or enum. For that use [TypeConstructorParseNode].
 *
 * @property name The name of the type not including any generic parts. (ex. `Map`)
 * @property genericOver The type parameters of a generic type. (ex. `<String, Int>`). If the type has no generic parameters the list will be empty.
 * @property projectLocation The path **in the source code** of the type. This is not the true path of the type. To get
 * the true path this path must be contacted with any imports in the file.
 */
class TypeParseNode(
    val name: IdentifierParseNode,
    val genericOver: List<TypeParseNode>, //non generic types have no generic parameters (ie. Int == Int<>)
    val projectLocation: ProjectLocation,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation), FunctionReferenceParseNode