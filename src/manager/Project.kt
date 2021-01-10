package manager

import extensions.truncate

data class ProjectFiles(val libraries: List<Library>)
data class Library(val name: String, val projectFiles: List<ProjectFile>)
data class ProjectFile(val fileLocation: FileLocation, val text: String) {
    override fun toString() = "fileLocation=$fileLocation, text=${text.truncate(15)}"
}