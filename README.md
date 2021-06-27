Plow has been relocated here https://github.com/PlowLang/Plow.

# Plow #
Plow is a WIP programming language targeting the ti84+ce calculator. Any suggestions about anything are welcome and encouraged.

## Language Overview ##

### General ###
Plow is a statically typed, type-safe, object-oriented language with functional elements. Meaning: Everything is an object, Inheritance, Polymorphism, Protocols (Interfaces), 
first-class functions, closures.

### Object Model ###
There will be 3 types of objects: Structs, Classes, and Enums.
 * Enums are going to be very simple. Just integers behind the scene. No special features like associated values for now.
 * Classes are just like they are in Java and family. Heap allocated reference types
 * Structs are like classes but value types. They live on the stack and can't be inherited but they can conform to protocols.
  
  You may notice that this is Swift's object model. That's because this is sort of a mashup between Kotlin and Swift.
  
I am also planning on adding tuples at some point.

### Generics ###
Generic objects will be implemented with type erasure \*groans from the crowd\*. Yes, yes I know but it seems like the simplest method. I am open to suggestions.
Generic functions will also be type erasure but any interface methods will be passed in by the compiler as well because interface methods calls are probably 
going to be pretty slow.

### Nullibility ###
Nullability will be safe and marked with a `?` after the type. ie. `Foo?`. I'm still undecided about the exact method for accomplishing this. Suggestions welcome.

### Error Model ###
To start with there will two error systems. A very simple `abort`/`die`/`panic` will immediately exit the process. (Which word do you like better?)
This will be what is used for stuff like divide by zero, array index out of bounds, etc.
The second system will be returning nullable types/special objects/tuples. I might add exceptions in the future but right now that feels too complicated for such a simple
platform.

### Type System ###
There is going to be some type inference, but only one-directional to start with. Function types will always have to be specified explicitly.

## Code Sample ##

```swift
protocol TreeNode<T: Equatable>: Equatable<TreeNode<T>> {
  func traverse(traverser: TreeTraverser<T>)
  //the tree node protocol require the equals function becuase it inherits from equatable
}

/// A simple binary tree implementation
class BinaryTree<T: Equatable>: TreeNode<T> {
  var left: TreeNode<T>? = null
  var right: TreeNode<T>? = null
  
  init() {}
  init(left: TreeNode<T>?, right: TreeNode<T>?) {
    this.left = left
    this.right = right
  }
  
  // satisfies the TreeNode protocol
  // the -> Unit is not required
  override func traverse(traverser: TreeTraverser<T>) -> Unit {
    traverser.apply(this)
    left?.traverse(traverser)
    right?.traverse(traverser)
  }
  
  // satisfies the Equatable protocol
  // also allows the use of the == operator
  override func equals(other: TreeNode<T>) -> Bool = left == other && right == other
}

struct Name: TreeNode<Name>, StringConvertable {
  val first: String
  val last: String
  
  init(first: String, last: String) {
    this.first = first
    this.last = last
  }
  
  override func traverse(traverser: TreeTraverser<Name>) {
    traverser.traverse(this)
  }
  
  //alternate funcition form for when the body is just an expression
  override func toString() -> String = first + ", " + last
  
  override func equals(other: TreeNode<Name>) -> Bool == other is Name && ((other as Name).first == first && (other as Name).last == last)
}

protocol TreeTraverser<T: Equatable> {
  func apply(t: TreeNode<T>)
}

class PrintTraverser<T: (Equatable, StringConvertable)> {
  override func apply(t: TreeNode<T>) {
    println(t.toString())
  }
}

main {
  var node: BinaryTree<Name> = BinaryTree( Name("Jane", "Doe"), Name("Bob", "Builder"))
  var tree: BinaryTree<Name> = BinaryTree( Name("Mateo", "C"), node)

  tree.traverse(PrintTraverser<Name>())  
  //output:
  //Mateo, C
  //Jane, Doe
  //Bob, Builder
}
```

There may be problems with this code. If something doesn't make sense please contact me.

## Current Progress ##
So far I have completed the lexer and a parser using antlr that generates a CST. I am currently working on converting the CST to a custom AST.

## Compilation Pipeline ##
The current plan is ANTLRv4 -> Custom AST -> Semantic Analyzer -> LLVM -> Binary. This, like everything, is subject to change.

## Future Plans ##
See my Trello: https://trello.com/b/xwbl9FeO/plowlang

## Q & A ##
### Is this going to actually be useful? ###
I don't know. Probably not. This is more of a learning project. If it turns out to actually be useful that's great! 
If you have any suggestions on how to make it more practical feel free to make them.

### Are you accepting outside help? ###
First of all, I'm not really expecting anyone to want to contribute to this but if you do: Maybe. This is mostly a learning project
as I said so I don't want other people writing large swaths of it. Little edits will always be welcome. If however, it turns out
to be useful then I will definitely accept community contributions.

### I have another question/want to contact you. ###
Please see the cemetech thread: https://www.cemetech.net/forum/viewtopic.php?p=290964#290964
