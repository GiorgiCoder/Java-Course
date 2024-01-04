Task:

Hungry Penguin

In this assignment, pingu should be helped on its search for fish. Pingu
lives in a world of rectangular shape which is sub-divided into tiles or
fields. Each individual field may represent different terrains where the
field occupied by pingu before a move determines how she may proceed
during the move. Pingu starts at a particular field and may perform
arbitrarily many moves. It is (among others) your task, for a given
world and a given initial position of Pingu, to determine whether Pingu
may reach a field containing a fish.

A world is represented by a 2-dim. array of int values. Each field of
this world belongs to the particular terrain indicated by the int-value
of the corresponding entry of the array. The following values may occur:

0: Land: on land, Pingu is slow. Pingy may proceed in each direction
(i.e., left, right, up or down) only by a single field. 1: Ice: ice is
slippery! Pingu may only proceed along one of the diagonals by a single
field. 2: Water: from this field, Pingu may proceed in any of the
diagonals exactly three fields. The two fields in-between are jumped
over. 3: Shark: Danger! Pingu must not stay on a field with a shark and
thus also not perform a move ending on such a field. 4: Fish: This is
the place of Pingu\'s strongest desire! Moves which lead out of the
world (obviously) are not permitted.

Implementation Implement the following functions.

1\. Output of the World. Implement the method public static void
printWorld(int\[\]\[\] world, int pinguRow, int pinguColumn), which
prints the world to the console. The first dimension indexes the rows,
while the second refers to the columns of the world. When outputting the
world, take care that

A field with Land should be represented as an \'L\'. A field with Ice is
represented as an \'I\'. A field with Water is represented as an \'W\'.
A field with a shark is represented as an \'S\'. A field with Fish is
represented as an \'F\'. Pingu is located at the field in row pinguRow
and column pinguColumn. This location is indicated by putting \"(P)\"
behind the terrain. The range of values of pinguRow and pinguColumn
thereby starts with 0. The individual columns are separated by a tab
(\'\\t\'). In front of the first and behind the last column there is no
tab.

2\. Can Pingu Reach a Fish? Realize a method public static boolean
isFishReachable(int\[\]\[\] world, int pinguRow, int pinguColumn). Your
method is meant to determine whether Pingu, when starting at location
pinguRow, pinguColumn, may reach along arbitrarily many moves a field
with Fish. If Pingu starts at a field with Shark, she obviously will be
eaten herself, and thus cannot reach Fish anymore. In this case, your
method therefore should return false.

Solve this problem by means of recursion. Thereby, you may very well
define and call a recursive auxiliary method. Take care that you do not
let Pingu run in circles (leading to non-termination).
