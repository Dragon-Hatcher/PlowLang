package main

import manager.Manager
import java.io.File

var filepath = "C:\\Users\\danie\\Dropbox\\Personal\\Daniel\\Code\\tilCode\\TestingCode"

fun main(args: Array<String>) {
    Manager().compile(File(filepath))
}