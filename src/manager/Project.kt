package manager

import extensions.truncate

/**
 * An object that holds the source code for a specific source file along with its location.
 */
data class ProjectFile(val fileLocation: FileLocation, val text: String) {
    override fun toString() = "fileLocation=$fileLocation, text=${text.truncate(15)}"
}

/**
 * A collection of [ProjectFile]s.
 */
data class Library(val name: String, val projectFiles: List<ProjectFile>)

/**
 * A collection of [Library]s.
 */
data class ProjectFiles(val libraries: List<Library>)
