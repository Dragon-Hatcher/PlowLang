package extensions

fun String.truncate(chars: Int) =
    if(this.length <= chars) this
    else this.subSequence(0, chars - 3).toString() + "..."

fun String.normalizeNewLines() = this.replace("\\n\\r?".toRegex(), "\n")
