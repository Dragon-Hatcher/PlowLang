package manager

import utils.givePlowError
import utils.mergeMaps
import utils.pathFromParent
import utils.toType
import java.io.File

class FileManager {

    fun getCodeFiles(file: File): Map<ProjectFileLocation, String> {
        val libsAndModules = getLibsAndModulesAsMap(file)
        return libsAndModules.map { lib -> lib.value.map { mod -> getModuleCodeFiles(lib.key, mod.key, mod.value) }.mergeMaps()}.mergeMaps()
    }

    private fun getModuleCodeFiles(lib: String, mod: String, modFile: File): Map<ProjectFileLocation, String> {
        val plowFiles = modFile.walkTopDown().filter{it.isFile && it.extension == "plow"}
        return plowFiles.map{ProjectFileLocation(lib, mod, it.pathFromParent(modFile)) to it.readText()}.toMap()
    }

    private fun getLibsAndModulesAsMap(file: File): Map<String, Map<String, File>> {
        val libs = HashMap<String, File>()
        libs.putAll(getSourceFile(file))
        libs.putAll(getLibFiles(file))

        return libs.map {it.key to getLibModules(it.value) }.toMap()
    }

    private fun getSourceFile(file: File): Map<String, File> {
        val projectContents = file.listFiles()
        val filesNamedSrc = projectContents.filter{it.name == "src"}
        val possibleSources = filesNamedSrc.filter{it.isDirectory}
        if(possibleSources.size != 1) {
            when {
                possibleSources.size > 1 -> givePlowError(PlowMultipleSrcFoldersError(possibleSources.toType(::PlowFolderNote)))
                filesNamedSrc.isNotEmpty() -> givePlowError(PlowNoSrcFolderError(filesNamedSrc.toType(::PlowNonFolderSrcNote)))
                else -> givePlowError(PlowNoSrcFolderError())
            }
        }
        return mapOf("src" to possibleSources[0])
    }

    private fun getLibFiles(file: File): Map<String, File> {
        val projectContents = file.listFiles()
        val libs: MutableMap<String, File> = HashMap()
        val possibleLibs = projectContents.filter { it.isDirectory && it.name == "lib" }
        if(possibleLibs.size > 1) {
            givePlowError(PlowMultipleLibFolderError(possibleLibs.toType(::PlowFolderNote)))
        } else if (possibleLibs.size == 1) {
            possibleLibs[0].listFiles()!!.forEach { if(it.isDirectory) libs["lib/"+it.name] = it }
        }
        return libs.toMap()
    }

    private fun getLibModules(file: File): Map<String, File> =
        file.listFiles()!!
            .filter{it.isDirectory}
            .map{it.name to it}
            .toMap()

}