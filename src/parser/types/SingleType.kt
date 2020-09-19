package parser.types

class SingleType(val type: String, typeLocation: TypeLocation): Type(typeLocation) {
    override fun toString(): String = type
}