package lexer

import manager.FileLocation

/**
 * A source code file that has been converted to a list of [LexToken]s.
 */
data class LexedFile(val location: FileLocation, val tokens: List<LexToken>)

/**
 * All the source code files for a project converted to [LexedFile]s.
 */
data class LexedProject(val files: List<LexedFile>)
