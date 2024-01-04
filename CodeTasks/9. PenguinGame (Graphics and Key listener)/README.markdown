Task:

Penguin game in Antarctica // to play a game, one would only need to add
images of fish, penguin, whale and seals. The logic is written in the
code.

At the beginning of the semester you already simulated the population of
penguins. Now you will go one step further and simulate a small (very
simplified) part of the Antarctic ecosystem. We consider a square, tiled
section of the world Antarctica with a fixed side length of 41. In
Antarctica, the tiles are represented by a two-dimensional array of
Animals, where a null value represents an empty square. The world is
cyclical, i.e. if an animal runs out of the world on the left, it is
placed on the field furthest to the right of the corresponding line (and
vice versa). Same goes for top and bottom.

The world coordinate system behaves in such a way that tiles with y
value 0 are at the top, and higher y values further down.

Whale can eat Penguins and Leopard Seals. (not fish) Leopard seals can
eat fish. Penguins can eat fish. No other relations.

Animals are instances of a subclass of the abstract class Animal. They
have an alive attribute that causes them to be drawn. Dead animals are
not drawn.

A method public boolean canEat(Animal animal) is already implemented in
the template for each animal, which returns whether this animal can eat
any other animal, according to the hierarchy shown above (arrows
indicate that this animal species eats another, e.g. the whale eats
penguins). However, this method still needs implementations of the
following methods in each of the subclasses of Animal:

protected abstract boolean eatenBy(Penguin penguin); protected abstract
boolean eatenBy(PlayerPenguin playerPenguin); protected abstract boolean
eatenBy(Whale whale); protected abstract boolean eatenBy(LeopardSeal
leopardSeal); protected abstract boolean eatenBy(Fish fish);

Movement of animals Depending on the animal species, the animals have
similar movement patterns. Basically, the animals have the following
movement preferences:

Fish: 1. Up, 2. Right, 3. Down, 4. Left All other species (Penguin,
LeopardSeal, Whale): 1. Left, 2. Above, 3. Right, 4. Below First, the
animals check the adjacent fields in the given order to see if there is
food there. If so, they move to the first such square where the move is
allowed.

If they cannot move to a square with food, they move to the first
allowed square (also in the given order).

Moves to a space with another animal that cannot be eaten by the moving
animal are not allowed. Also, animals never enter a space that is
adjacent to a hunter in any of the four directions. If an animal does
not have a legal move, it does not move.

There is also a class PlayerPenguin which is a subclass of Penguin but
is controlled by the player.

There is also a class called Player Penguin which is a subclass of
Penguin but controlled by the player:

1.  Whale
2.  LeopardSeal
3.  Penguin
4.  Fish

Animals of the same kind are moved in the order of their occurrence in
the respective array. Eaten animals lose their lives and disappear from
the tile. Dead animals no longer move.

The end of the game

The player's goal is to move to the other penguin's space. The player
wins the moment he moves to the space where the other penguin is. The
move of the other penguin or of all other animals is then no longer
carried out. The player loses if he moves onto a space containing a
leopard seal or a whale. Also, the player loses if the player penguin or
the other penguin is eaten.

Implementation of gameplay

Implement the public void move() method in the Animal and Fish classes.
Movements (example) Implement the public boolean move(int newX, int
newY) method in the PlayerPenguin class. Their method places the player
in the appropriate position. It also returns true if the game is over at
that moment because you won or lost. There is no need to check whether
the player teleports, i.e. whether the new position is really only one
step away. Player Movement (Example 1) Player Movement (Example 2)
Implement the private static void moveAll() method in the Antarctica
class. The method should: Perform the movements using the implemented
move methods in the order described above. During the movement, the
corresponding animals should be eaten. Complete the game loop in the
private static void gameLoop() method in the Antarctica class. The loop
should run as long as the game is not over. After drawing the playing
field with draw(), the playerPinguin should be moved one step in the
direction that the user wants. To do this, access the currentEvent
attribute in the method by either NOTHING, if no key was pressed or UP,
DOWN, LEFT or RIGHT if the user pressed one of the arrow keys. If a
button was pressed, the player penguin should be moved to the
corresponding new position. If the game is not yet over, the movements
of the other animals are carried out after the player's turn. At the end
of each iteration, set the value of currentEvent back to NOTHING. (This
method must not be recursive under any circumstances, otherwise it can
lead to stack overflows when playing for a very long time!)
