package manager.exceptions

import errors.PlowError
import java.io.File

class ProjectRootMustBeDirectoryError(passedRoot: File): PlowError(
    "ProjectRootMustBeDirectory",
    "The passed project root (${passedRoot.name}) is not a directory.",
    listOf()
)