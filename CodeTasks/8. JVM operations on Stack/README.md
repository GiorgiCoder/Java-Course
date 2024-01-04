Task:

MiniJVM Simulator

We want to develop a simulator for a subset of the MiniJVM code. The
following commands should be implemented:

arithmetic operations: ADD, SUB Load constants: CONST i Allocating
memory space ALLOC i Load/Store: LOAD i, STORE i Comparison operators:
LESS Jump instructions: JUMP i, FJUMP i End of program: HALT Each
operation should be implemented in its own class, which inherits from
the abstract class Instruction. A simulator has an array of instructions
code and a stack stack. The stack stores int values. For the sake of
simplicity, ints is used as the internal representation of truth values
in this exercise. The simulator\'s stack has a fixed capacity, which is
specified during instantiation. The stackPointer of the stack points to
the top occupied cell. In addition, an instance of Simulator has a
program counter programCounter, which stores the index of the next
command to be executed and an attribute halted that stores whether a
HALT was executed.

The files already exist in the template. The abstract class Instruction
is already defined like the classes Simulator and Stack. Complete the
following tasks: 1. Create the classes for the instructions in the given
files. Choose the class names so that they match the given file name.
The classes should inherit from the abstract class Instruction.

2\. The objects of the classes representing the instructions for CONST
i, ALLOC i, LOAD i, STORE i, JUMP i, FJump i, should each have an int
attribute, which stores the i parameter of the command. In the classes
for these commands, create a public constructor that expects an int
parameter and initializes the respective attribute with it.

3\. For each concrete instruction class, implement the public void
execute(Simulator simulator) method, which changes the stack
appropriately depending on the instruction. The behavior of
execute(\...) for the different instructions is as follows: - ADD: Pops
the top two elements off the stack and pushes the result of the addition
onto the stack. - SUB: Pops the top two elements a, b from the stack and
pushes the result of b - a onto the stack. - CONST i: Loads the constant
i onto the stack - ALLOC i: Increases the stack pointer by i. You can
use Stack\'s alloc method to do this. - LOAD i: Reads the value at index
i of the stack and pushes it onto the stack. - STORE i: Pops the top
value off the stack and stores it at index i of the stack. - LESS: Pops
the top two elements a, b from the stack and pushes the result of the
comparison b \< a encoded as int onto the stack. The truth value true
should be coded by a 1, the truth value false by a 0. - JUMP i should
implement a jump to the command at index i. - FJump i Pops the top
element off the stack. If the value of this element is 0 then a jump
should be made to the command at index i. Otherwise, no jump is
performed and program flow continues normally. - HALT should end the
program. To do this, the halted attribute of the simulator should be set
to true.
