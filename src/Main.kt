import manager.loadProjectFiles
import java.io.File

val path = "C:\\Users\\danie\\Dropbox\\Personal\\Daniel\\Code\\tilCode\\TestingCode"

fun main() {
    val projectFiles = loadProjectFiles(File(path))
}