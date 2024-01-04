public class Song {
    private String title;
    private int releaseYear;
    private int duration;
    private int likes;


    public boolean changeDuration(int duration){
        if(duration<0 || duration>720 || duration == this.duration) {
            return false;
        }else{
            this.duration = duration;
            return true;
        }
    }

    public boolean isEqual(Song other){
        if(this.title==other.title && this.releaseYear==other.releaseYear &&
                this.duration == other.duration && this.likes==other.likes)
            return true;
        else
            return false;
    }

    void like(){
        likes++;
    }

    void unlike(){
        likes--;
        if(likes<0)
            likes=0;
    }

    public Song(String title, int releaseYear, int duration, int likes){
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.likes = likes;
    }
    public Song(String title, int releaseYear, int duration) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.likes = 0;
    }
    public Song(String title, int releaseYear){
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = 60;
        this.likes = 0;
    }

    @Override
    public String toString() {
        double inMinutes = duration/(double)60;
        return "Title:"+title+",Duration:"+String.format("%.2f", inMinutes)+" minutes,ReleaseYear:"+releaseYear+",Likes:"+likes;
    }

    public String getTitle() {
        return title;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public int getDuration() {
        return duration;
    }
    public int getLikes() {
        return likes;
    }
}
