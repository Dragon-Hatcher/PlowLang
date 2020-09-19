package utils

import java.io.File

fun Char.matches(regex: Regex): Boolean {
    return this.toString().matches(regex)
}

fun <T> T.andPrint(): T {
    println(this.toString())
    return this
}

fun File.pathFromParent(parent: File) = this.absolutePath.removePrefix(parent.absolutePath)

fun <T, U> List<T>.toType(mapper: (T) -> U): List<U> =
    this.map{mapper(it)}

fun <K, V> List<Map<K, V>>.mergeMaps(): Map<K, V> = this.fold(HashMap<K, V>()){acc, map -> acc.putAll(map); acc}