public class SpotiJy {

    public static void main(String[] args){
        Song luv = new Song("luv", 1989,700);
        Song pi = new Song("pi",1900, 200, 15);
        Song rock = new Song("rock",1945, 300, 16);
        Song yes = new Song("yes",1945);
        Album myAlbum = new Album("1st album", 2019, new Song[] {pi, yes});
        Song[] songs = new Song[] {pi, luv, rock, yes};
    }
}