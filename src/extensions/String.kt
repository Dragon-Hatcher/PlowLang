package extensions

/**
 * Returns this string truncated with ... if it is longer than the given length. The returned string will alwaus be <=
 * the given length.
 */
fun String.truncate(chars: Int) =
    if(this.length <= chars) this
    else this.subSequence(0, chars - 3).toString() + "..."

/**
 * Returns this string with all types of new lines replaced with the \n new line.
 */
fun String.normalizeNewLines() = this.replace("\\n\\r?".toRegex(), "\n")
