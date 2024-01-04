public class Artist{
    private String firstName;
    private String lastName;
    private int birthYear;
    private int[] albums;
    private Song[] songs;

    public static void main(String[] args){

    }

    public Artist(String firstName, String lastName, int birthYear, int[] albums, Song[] songs){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.albums = albums;
        this.songs = songs;

    }
    public Song mostLikedSong(){
        Song mostLikedSong = songs[0];
        for(int i = 0; i < songs.length; i++){
            if(songs[i].getLikes()>songs[0].getLikes())
                mostLikedSong = songs[i];
        }
        return mostLikedSong;
    }
    public Song leastLikedSong(){
        Song leastLikedSong = songs[0];
        for(int i = 0; i < songs.length; i++){
            if(songs[i].getLikes()<songs[0].getLikes())
                leastLikedSong = songs[i];
        }
        return leastLikedSong;
    }

    public int totalLikes(){
        int totalLikes = 0;
        for(int i=0; i < songs.length; i++) {
            totalLikes = totalLikes + songs[i].getLikes();
        }
        return totalLikes;
    }
    boolean isEqual(Artist other){
        if(this.firstName==other.firstName && this.lastName==other.lastName &&
                this.birthYear == other.birthYear)
            return true;
        else
            return false;
    }
    public String toString() {
        return "Name:"+firstName+" "+lastName + ",Birth year:"+birthYear+",Total likes:"+ totalLikes();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int[] getAlbums() {
        return albums;
    }

    public Song[] getSongs() {
        return songs;
    }
}