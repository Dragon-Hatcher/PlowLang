package parser.nodes

import manager.CodeLocation
import manager.ProjectLocation

/**
 * Represents an identifier reference.
 *
 * @property name The name of the identifier.
 * @property projectLocation The path **in the source code** of the identifier reference. This is not the true path of
 * the identifier. To get the true path this path must be contacted with any imports in the file.
 */
class IdentifierReferenceParseNode(
    val name: IdentifierParseNode,
    val projectLocation: ProjectLocation,
    codeLocation: CodeLocation
): BaseParseNode(codeLocation), ExpressionParseNode, FunctionReferenceParseNode