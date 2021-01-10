package manager

data class FileLocation(val files: List<String>)
data class TextLocation(val line: Int, val col: Int)
data class CodeLocation(val fileLocation: FileLocation, val textLocation: TextLocation)
data class NamespaceLocation(val namespaces: List<String>)
data class ProjectLocation(val fileLocation: FileLocation, val namespaceLocation: NamespaceLocation)
