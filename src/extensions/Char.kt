package extensions

fun Char.matches(regex: Regex) = this.toString().matches(regex)