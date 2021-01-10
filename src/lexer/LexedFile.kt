package lexer

import manager.FileLocation

data class LexedFile(val location: FileLocation, val tokens: List<LexToken>)
data class LexedProject(val files: List<LexedFile>)
