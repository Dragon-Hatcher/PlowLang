package parser

class UnexpectedTokenException(message: String): Exception(message)
class ConsecutiveScopeDeclarationsException(message: String): Exception(message)
class UndefinedArgumentNameException(message: String): Exception(message)