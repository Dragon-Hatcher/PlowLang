package parser.types

class GenericType(val type: SingleType, val genericOver: List<Type>, typeLocation: TypeLocation): Type(typeLocation) {
    override fun toString(): String = type.toString() + genericOver.joinToString(prefix = "<", postfix = ">")
}