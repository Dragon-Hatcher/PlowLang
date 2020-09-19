package manager

data class ProjectFileLocation(val lib: String, val mod: String, val file: String) {
    override fun toString() = "$lib/$mod/$file"
}