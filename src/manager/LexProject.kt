package manager

import lexer.LexedFile
import lexer.LexedProject
import lexer.Lexer

/**
 * Takes a [ProjectFiles] object and converts it to a [LexedProject] object by lexing each file using the [Lexer] class.
 */
fun lexProject(project: ProjectFiles): LexedProject {
    val allFiles = project.libraries.map { it.projectFiles }.flatten()
    val lexedFiles = allFiles.map { LexedFile(it.fileLocation, Lexer(it).getTokens()) }
    return LexedProject(lexedFiles)
}