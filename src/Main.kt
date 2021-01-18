import extensions.fileLocation
import manager.lexProject
import manager.loadProjectFiles
import java.io.File

val path = "C:\\Users\\danie\\Dropbox\\Personal\\Daniel\\Code\\tilCode\\TestingCode"

fun main() {
    println(File(path).fileLocation(File("C:\\Users\\danie\\Dropbox\\Personal\\Daniel\\Code")))

//    val projectFiles = loadProjectFiles(File(path))
//    val lexedProject = lexProject(projectFiles)
}