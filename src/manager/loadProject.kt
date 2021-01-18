package manager

import extensions.fileLocation
import extensions.isPlowFile
import manager.exceptions.MissingSrcFolderError
import manager.exceptions.ProjectRootMustBeDirectoryError
import java.io.File

/**
 * Finds all the .plow files in a project given the [rootFile] of the project. Returns a [ProjectFiles] object.
 */
fun loadProjectFiles(rootFile: File): ProjectFiles {
    if(!rootFile.isDirectory) ProjectRootMustBeDirectoryError(rootFile).throwSelf()

    val folders = rootFile.listFiles()!!.filter { it.isDirectory }
    val src = folders.firstOrNull { it.name == "src" }
    val lib = folders.firstOrNull { it.name == "lib" }

    if(src == null) MissingSrcFolderError().throwSelf()

    val libraryFiles = mutableListOf<File>()
    libraryFiles.add(src)
    if(lib != null) libraryFiles.addAll(lib.listFiles()!!.filter { it.isDirectory })

    fun getLibraryFromFile(lib: File): Library {
        val allFiles = lib.walk().toList()
        val plowFiles = allFiles.filter { !it.isDirectory && it.isPlowFile() }
        val projectFiles = plowFiles.map { ProjectFile(it.fileLocation(lib), it.readText()) }
        return Library(lib.name, projectFiles)
    }

    val libraries = libraryFiles.map { getLibraryFromFile(it) }
    return ProjectFiles(libraries)
}

