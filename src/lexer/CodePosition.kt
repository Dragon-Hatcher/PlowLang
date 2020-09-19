package lexer

import manager.ProjectFileLocation

data class CodePosition(val fileLocation: ProjectFileLocation, val line: Int, val col: Int) {
    override fun toString(): String = "on line $line, col $col in $fileLocation"
}