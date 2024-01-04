Task:

Penguins and Company


In this assignment you are required to implement three packages. One package corresponds to generic tree, while the other two are for independent problems. You must use your own implemented data structure to solve each of the problems.

Package tree:

In this package we have two classes.


Class Node

Node consists of 3 private field. List<Node<T>> children – this field aims to store direct children of the node. Node<T> parent – this field aims to keep track of parents for each node. T data – generic field, which encapsulates data stored inside the node. Corresponding constructor and getter methods are already provided.

You have to implement 5 methods.

insert(Node<T> value) - this method adds new node as a direct child of a Node object on which it was called.

isLeaf() - this method determines whether given node is leaf or not. A node is a leaf if and only if it doesn’t have any children.

size() - this method returns the size of the tree rooted with a given node;

remove() -this method removes aNode object on which it was called from the tree and assigns its direct children to its parent;

traversal() - this method traverses through the tree rooted with a Node object on which it was called and prints the data (T data) onto the console. You may use InOrder, PreOrder or PostOrder traversals. (en.wikipedia.org/wiki/Tree\_traversal)


Class Tree

Tree consists of only 1 private field, that is Node<T> root; Instance of tree is rooted in field root. Corresponding constructor and getter methods are already provided.

You have to implement 6 methods.

insert(T value, T parent) - If the value is already present in the tree or parent object is not present in the tree, then the method should do nothing. Otherwise, it should add value to the tree and add them as parents child.

remove(T value) - If the value is not present in the tree, then the method should do nothing. Otherwise, it should remove node with the corresponding value from the tree and assign it’s direct children to it’s parent. Also, once the root of the tree is captured, it should never be removed from the tree. Therefore, method should also do nothing if passed parameter is root data.

T LCA(T a, T b) - this method returns lowest common ancestor of a and b in the tree, provided these two values are present.

distanceBetweenNodes(T a, T b) - returns the number of edges on the path from a to b (or b to a, they are the same, anyway)

containsKey(T key) - checks if the given key exists in the tree;

traversal() - Like Node’s traversal method, but starts traversing from the root of the tree. Avoid code duplication while implementing this method. You may add arbitrary methods as you see fit that will help you to write the perfect implementation, but make sure to mark them as private, so internal implementation is not visible to the outer world.



Package penguinDate:

On a lonely island in the middle of the pacific, penguins live. Penguins are so sad now since they were denied of participating in human world cup in football. So, to escape boredom they decided to ask each other on a date. Penguin can offer a date (or agree) to the other penguin if and only if the distance in the genealogy tree between this penguin and lowest common ancestor of these two penguins is greater or equal to allowance (field of class Penguin); Classes Penguin and DateSimulator are already provided for you. However, you may add more simulations to ensure your code works correctly. In this problem, you must write implementation of just one method. That is canGoOnADate(Penguin p1, Penguin p2) inside the class PenguinDateGenerator. The method returns true if two penguins can go on a date together (They are that simple) and false otherwise. You may see the class DateSimulator for examples.



Package company:


Each employee is uniquely determined by their ID.


Class Company


We aim to model the structure of the company inside our own implemented generic data structure.

We have 6 private fields inside this class.

String name - We store the name of the company inside this attribute.

Tree<Integer> employeesTree - We model our company inside this attribute. Each employee’s parent is their boss. Since, ID uniquely determines the employees, to write the most efficient code and save memory, we declare integer as a generic type.

Map<Integer,Employee> employees - this field links each ID with the corresponding employee;

Queue<Integer> availableIDs - When an employee leaves the company, ID is freed. Inside this attribute, we store available ID’s that were once taken by some employees. When new employee is registered into the firm, the ID with the lowest integer value is assigned to them.

Employee ceo - CEO can never be removed from the company.

Static int availableID - determines which ID number can be assigned to a new employee, only if queue is empty.


You must implement 4 methods in this class.

addEmployee(Employee newEmployee) - If boss of this new employee is not present in the tree, then method should do nothing. ID of new employee is determined and assigned in this method.

fireEmployee(int ID) - the method should fire employee with the given ID from the company;

findCommonBoss(Employee e1, Employee e2) - This method should return the lowest common boss of the two employees;

findByID(int ID) - This method returns the employee with given ID. Null if the employee is not present.


Class CompanySimulation


In this class you have to finish the simulation of the company. A string is read from the console. You have to parse this string and perform corresponding operations.

EmployeeWithID ID - If the string has the following form, then employee with given ID should be printed on to the console. If employee is not present in the company, an appropriate error message should be displayed;

Add name ID - If the string has the following form, then we should check if the employee in the tree exists with given ID. If employee is not present, we should print corresponding message that boss doesn’t exist, otherwise add a new employee with given name as their subordinate.

Fire id - If the employee with given ID doesn’t exist, we should print the corresponding message, otherwise fire the employee.

FindCommonBoss ID1 ID2 - If either ID1 or ID2 is not present in the company, we should print the corresponding message, otherwise find lowest common boss of two employees. If user decides to input string in any other form, the corresponding error message should be printed. We can handle that by means of try/catch;
