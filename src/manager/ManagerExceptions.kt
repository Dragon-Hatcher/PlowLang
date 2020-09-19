package manager

import utils.PlowError
import utils.PlowNote
import java.io.File

class PlowNoSrcFolderError(nonFolderSrc: List<PlowNonFolderSrcNote> = listOf()) : PlowError(
    "NoSrcFolder",
    "No src folder was found.",
    nonFolderSrc.map{ it })

class PlowNonFolderSrcNote(file: File): PlowNote(
    "Found a non folder file named src: $file.")

class PlowMultipleSrcFoldersError(srcFolders: List<PlowFolderNote>) : PlowError(
    "MultipleSrcFolders",
    "Multiple src folders were found.",
    srcFolders.map{ it })

class PlowFolderNote(file: File): PlowNote(
    "Here: $file.")

class PlowMultipleLibFolderError(srcFolders: List<PlowFolderNote>) : PlowError(
    "MultipleLibFolders",
    "Multiple lib folders were found.",
    srcFolders.map{ it })
