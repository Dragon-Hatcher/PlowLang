package extensions

import manager.FileLocation
import java.io.File

fun File.isPlowFile() = this.extension == "plow"

fun File.fileLocation(lib: File): FileLocation {
    val libPath = lib.absolutePath
    val myPath = this.absolutePath
    val pathFromLib = myPath.removePrefix(libPath).removePrefix(File.separator)
    val files = listOf(lib.name) + pathFromLib.split(File.separator)
    return FileLocation(files)
}
