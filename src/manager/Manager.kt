package manager

import lexer.Lexer
import parser.Parser
import java.io.File

class Manager {

    fun compile(file: File) {
        val codeFiles = FileManager().getCodeFiles(file)
        val lexer = Lexer()
        val lexedCodeFiles = codeFiles.map{it.key to lexer.lex(it.key, it.value)}.toMap()
        val parser = Parser(lexedCodeFiles.keys)
        val parsedCodeFiles = lexedCodeFiles.map{it.key to parser.parse(it.value)}.toMap()
        println(parsedCodeFiles)
    }



}

