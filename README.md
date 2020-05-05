##SimpleInterpreter

An interpreter for a simple language.


## Usage instructions:
	
#### Compile

	javac Interpreter.java

### Run options:

#### CLI:
	
	java Interpreter

or

#### execute a sequence of commands from a file:

	java Interpreter <file>


## Grammar

Program:

	Assignment*

Assignment:

	Identifier = Exp;

Exp: 

	Exp + Term | Exp - Term | Term

Term:

	Term * Fact  | Fact

Fact:

	( Exp ) | - Fact | + Fact | Literal | Identifier

Identifier:

	Letter [Letter | Digit]*

Letter:

	a|...|z|A|...|Z|_

Literal:

	0 | NonZeroDigit Digit*	

NonZeroDigit:

	1|...|9

Digit:

	0|1|...|9