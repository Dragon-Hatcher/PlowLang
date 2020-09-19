package parser.types

class FunctionType(val argTypes: List<Type>, val returnType: Type?, typeLocation: TypeLocation) : Type(typeLocation) {
    override fun toString(): String = "(${argTypes.joinToString(prefix = "(", postfix = ")")}) -> ${returnType?:"Void"}"
}