package extensions

/**
 * Returns `true` if the character matches the given regex.
 */
fun Char.matches(regex: Regex) = this.toString().matches(regex)