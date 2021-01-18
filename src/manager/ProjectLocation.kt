package manager

/**
 * A file location in a project.
 */
data class FileLocation(val files: List<String>)

/**
 * A location in a source file.
 */
data class TextLocation(val line: Int, val col: Int)

/**
 * The location of a piece of source code made up of a [FileLocation] and a [CodeLocation].
 */
data class CodeLocation(val fileLocation: FileLocation, val textLocation: TextLocation)

/**
 * A namespace for an object.
 */
data class NamespaceLocation(val namespaces: List<String>)

/**
 * A full path to any object made up of a [FileLocation] and a [NamespaceLocation].
 */
data class ProjectLocation(val fileLocation: FileLocation, val namespaceLocation: NamespaceLocation)
