package parser.types

class TupleType(val types: List<Type>, typeLocation: TypeLocation): Type(typeLocation) {
    override fun toString(): String = types.joinToString(prefix = "(", postfix = ")")
}