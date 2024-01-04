Task:

SpotiJy

In this task, you should create a simple song manager program - SpotiJy.
It can be used to store data about songs, albums, and artists, and
update it according to users activities.

In this task you have to implement several classes - Song, Album ,Artist
and SpotiJy Each of them is responsible for storing and/or managing a
particular part of the data.

\-\-\-\-- Class Song \-\-\-\--

Song At first, you should implement Song class, as song is the smallest
data entity in our software. Each song should be described with
following attributes:

title - Title releaseYear - Release year duration - Duration in seconds
likes - Amount of likes (Just a number, measuring the popularity)

All 4 attributes should be private and should have getter methods
(String getTitle(), int getReleaseYear(), int getDuration(), int
getLikes()). Also, you should implement a constructor to create Song
objects.set 60 as a default value for the duration of the song and 0 for
the likes. Hint: You have to create more than one constructor.

Example

// all 3 are valid examples of creating a song: Song rattlestarSong =
new Song(\'Snake Jazz\', 1989) Song majorSong = new Song(\'Space
Oddity\', 1969, 315) Song queenSong = new Song(\'Teo Torriatte\', 1977,
355, 132178)

Since some songs can be cropped or extended,you have to provide setter
function boolean changeDuration(int duration) for duration attribute as
well, which returns false if the given parameter is less than 0, more
than 720 or same as it was before. Otherwise, it should return true and
set duration attribute equal to a new value.

Instead of creating a single setter method, you should create void
like() and void unlike() methods to manage the changes in song\'s
popularity. When called like() should increase likes amount by 1 and
unlike() should decrease it by 1.

Caveat: song\'s like counter shouldn\'t be below 0.

Song rattlestarSong = new Song(\'Snake Jazz\', 1989)
snakeJazz.getReleaseYear() // 1989

// By default, likes are 0, it increases by 1 when \`like()\` is called
snakeJazz.getLikes() // 0 snakeJazz.like() snakeJazz.getLikes() // 1

// Duration is 60 by default snakeJazz.getDuration() // 60
snakeJazz.setDuration(90) snakeJazz.getDuration() // 90

String toString() - override public method and output Song instance in
the following format: Title: \<title\>, Duration: , Release year: ,
Likes: where durationInMinutes is duration converted into minutes.

Example

\>\>\> Song snakeJazz = new Song(\'Snake Jazz\', 1989, 30) \>\>\>
write(snakeJazz.toString()) Title:Snake Jazz,Duration:0.5
minutes,Release year:1989,Likes:0

\-\-\-\-- Class Album \-\-\-\--

Album Next, you should create a class Album to store the collection of
songs in one object. The albums should have the following attributes:

title - Title releaseYear - The Year it was released songs - A
collection of songs, represented as an array of Song objects. All 3
attributes should be private and should have getter methods (String
getTitle(), int getReleaseYear(), Songs\[\] getSongs()).

The Album class should have a constructor that takes title and release
year as arguments and initializes appropriate attributes.

int addSongs(Song\[\] songs) - the procedure takes song array as an
argument and adds them to the \'songs\' collection. If the song is
already contained in the collection, no duplicate append should occur.
After songs are added, return the number of new songs appended.

to compare two songs,go back to the Song class and override public
method boolean isEqual(Song other): Two songs are considered to be the
same if they have exact same title, release year and duration. For
example, these 3 songs are considered to be same - Song(\'My Song\',
2011, 120, 1570), Song(\'My Song\', 2011, 120, 1570), Song(\'My Song\',
2011, 120, 7500) . While these 3 are different from each other -
Song(\'My Song\', 2011, 120, 450), Song(\'Other Song\', 2011, 120, 150),
Song(\'My Song\', 2011, 50, 450).

Example

\>\>\> Album greenSide = new Album(\"Green side\",1976) \>\>\>
greenSide.getTitle() \"Green side\" \>\>\> // One song is added to the
album \>\>\> greenSide.addSongs({snakeJazz}) 1 \>\>\> // 2 songs are
provided, but one of them is already part of the album \>\>\>
greenSide.addSongs({snakeJazz, majorSong}) 1 \>\>\>
greenSide.addSongs({repeatedSong, newSong, repeatedSong}) 2

Song\[\] shuffle() - returns the songs from \'songs\' collection in
random order. You can use random class provided by
java.(https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)

Song\[\] sortByTitle(boolean isAscending) - returns the songs sorted by
the lexicographical comparison of song titles. if isAscending is false,
return the songs in descending order.

Song\[\] sortByDuration(boolean isAscending) - returns the songs sorted
by the comparison of song Durations. if isAscending is false, return the
songs in descending order.

Song\[\] sortByReleaseYear(boolean isAscending) - returns the songs
sorted by the comparison of song release years. if isAscending is false,
return the songs in descending order.

Song\[\] sortByPopularity(boolean isAscending) - returns the songs
sorted by the comparison of popularity(likes). if isAscending is false,
return the songs in descending order.

static Song\[\] reverse(Song\[\] songs) - static function,which reverses
the order of the passed array and returns.

And lastly, for this class, implement a String toString() function,
which is just a textual representation of the album in the following
format - \'Title:{title},Release year:{release
year},songs:{{song1}\|{song2}\|{song3}...}\'. See examples for more
details:

Examples:

Album with no songs:

\>\>\> Album greenSide = new Album(\"Green side\",1976) \>\>\>
write(greenSide.toString()) \`Title:\`Green side,Release
year:1976,songs:{}

Album with a single song:

\>\>\> Song snakeJazz = new Song(\'Snake Jazz\', 1989, 30) \>\>\> \>\>\>
Album greenSide = new Album(\"Green side\",1976) \>\>\>
greenSide.addSongs(snakeJazz) \>\>\> write(greenSide) \`Title:\`Green
side,Release year:1976,songs:{Title:Snake Jazz,Duration:0.5
minutes,Release year:1989,Likes:0}

\-\-\-\-- Class Artist \-\-\-\--

Artist The Artist is the last entity class to implement. The artist
should have the following attributes:

firstName - A first name lastName - A last name birthYear - The year
person was born in albums - The collection of albums, represented as an
array of Album objects singles - The list of songs (That are not part of
any albums and are released seperately), represeted as an array of Song
objects. All 5 attributes should be private and should have getter
methods (String getFirstName(), String getSecondName(), int
getBirthYear(), Album \[\]getAlbums(), Song\[\] getSingles()).

Furthermore you should create a several methods in Artist class.

Create a method called Song mostLikedSong(),which will return the most
liked song of the artist. Note: your function should compare all the
songs from singles and all the albums.

Create a method called Song leastLikedSong() which behaves in the same
way as Song mostLikedSong() method, but instead of the most popular
song, it returns the least popular one.

Create a method int totalLikes() which returns the total number of likes
for this artist. Again, consider all songs from the albums and singles.

Creates a method boolean isEqual(Artist other) - compares two artists.2
Artist objects are considered to be the same, if they have the same
first name, last name and birth year.

And at the end, String toString() method should be implemented as well.
It should return the following representation of the artist: \'Name:
{firstName} {lastName},Birth year:{birthYear},Total likes:{total likes
of this artist}\'. Note: \'{\' and \'}\' are used to mark values and
should not be part of the final output and string representation of the
artist does not include albums and singles.

\-\-\-\-- Class SpotiJy \-\-\-\--

SpotiJy This is the final and the main class of this software - SpotiJy.

SpotiJy has a single hidden (private) attribute artists - a collection
of artist, represented as an array of Artist objects. A constructor does
not take any arguments and should initialize artists with an empty
array.

Furthermore, SpotiJy should have following methods:

Artist\[\] getArtists() - Returns the array of of artists. void
addArtists(Artist\[\] artists) - Adds Artist objects to the list, if
they are not already in the list. String \[\] getTopTrendingArtist() -
Returns the array of the first name and the last name (\[\'john\',
\'doe\'\]) of the artist that has the most likes - sum of likes from all
songs from all albums and singles. String getTopTrendingAlbum() -
Returns the name of the album with the most total likes - sum of like of
all songs in this album. It should compare all albums in all artists.
String getTopTrendingSong() - Returns the name of the song with the most
likes. It should consider all the songs in all artists albums or
singles. If there are several artists/albums/songs with same maximum
amount of likes, return any of them.
