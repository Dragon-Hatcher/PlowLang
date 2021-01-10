package manager.exceptions

import errors.PlowError

class MissingSrcFolderError: PlowError(
    "MissingSrcFolder",
    "A src folder could not be found.",
    listOf()
)