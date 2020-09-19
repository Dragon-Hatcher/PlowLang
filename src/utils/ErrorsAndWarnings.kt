package utils

import lexer.CodePosition

abstract class PlowNote(val noteMessage: String)
abstract class PlowWarning(val warningType: String, val warningMessage: String, val notes: List<PlowNote>)
abstract class PlowError(val errorType: String, val errorMessage: String, val notes: List<PlowNote>)

class CodePositionNote(position: CodePosition): PlowNote("The %WE% was found $position.")

class PlowException(message: String): Exception(message)

val plowWarnings: MutableList<PlowWarning> = ArrayList()
val plowErrors: MutableList<PlowError> = ArrayList()

fun givePlowWarning(warning: PlowWarning) =
    plowWarnings.add(warning)

fun givePlowError(error: PlowError) {
    plowErrors.add(error)
    throw PlowException("Plow error of type ${error.errorType}")
}
