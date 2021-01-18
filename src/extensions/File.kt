package extensions

import manager.FileLocation
import java.io.File

/**
 * Checks if the extension of this file is `.plow`
 */
fun File.isPlowFile() = this.extension == "plow"

/**
 * Gets the list of folders from the given file to this file.
 * Ex. If this is foo/bar/baz/qaz and the passed file is foo/bar the returned value will be bar/baz/qaz
 */
fun File.fileLocation(lib: File): FileLocation {
    val libPath = lib.absolutePath
    val myPath = this.absolutePath
    val pathFromLib = myPath.removePrefix(libPath).removePrefix(File.separator)
    val files = listOf(lib.name) + pathFromLib.split(File.separator)
    return FileLocation(files)
}
