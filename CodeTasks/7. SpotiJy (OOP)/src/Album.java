import java.util.Random;

public class Album{
    private String title;
    private int releaseYear;
    private Song[] songs;

    public Album(String title, int releaseYear, Song[] songs){
        this.title = title;
        this.releaseYear = releaseYear;
        this.songs = songs;
    }
    public int addSongs(Song[] songs) {
        int count = songs.length;
        for (int i = 0; i < songs.length; i++) {
            for (int j = 0; j < this.songs.length; j++) {
                if (songs[i].isEqual(this.songs[j]))
                    count--;
            }
        }return count;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < songs.length; i++) {
            if (i == 0 && i!= songs.length-1)
                sb.append("{" + songs[i].toString()+ "|");
            if(i==0 && i == songs.length-1)
                sb.append("{" + songs[i].toString()+ "}");
            if(i!=0 && i== songs.length-1)
                sb.append("|" + songs[i].toString()+ "}");
            if(i!=0 && i!=songs.length-1)
                sb.append("|" + songs[i].toString()+ "|");
        }
        return "Title:" + title + ",ReleaseYear:" + releaseYear + ",songs:"+ sb;
    }
    public Song[] shuffle(){
        Song[] randomArray = new Song[songs.length];
        Random random = new Random();
        for (int i = 0; i < randomArray.length; i++) {
            int randPos = random.nextInt(randomArray.length);
            Song temp = randomArray[i];
            randomArray[i] = randomArray[randPos];
            randomArray[randPos] = temp;
        }
        for(int i = 0; i < randomArray.length; i++){
            System.out.print(randomArray[i]+" ");
        }
        return randomArray;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Song[] getSongs() {
        return songs;
    }
}
