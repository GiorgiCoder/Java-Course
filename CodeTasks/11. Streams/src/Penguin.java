import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Penguin {
    private List<Geo> locations;
    private String trackID;

    public Penguin(List<Geo> locations, String trackID) {
        this.locations = locations;
        this.trackID = trackID;
    }

    @Override
    public String toString() {
        return "Penguin{" +
                "locations=" + locations +
                ", trackID='" + trackID + '\'' +
                '}';
    }

//  public static void main(String[] args) {
//    List<Geo> list = new ArrayList<>();
//    Geo g1 = new Geo(1.0, 4.0);
//    Geo g2 = new Geo(-3.5, 12.0);
//    Geo g3 = new Geo(1.0, 2.0);
//    list.add(g1);
//    list.add(g2);
//    list.add(g3);
//    Penguin penguin = new Penguin(list, "bruh");
//    System.out.println(penguin.toStringUsingStreams());
//  }

    public List<Geo> getLocations() {
        return locations;
    }

    public String getTrackID() {
        return trackID;
    }


    public String toStringUsingStreams() {
        List list;
        list = locations.stream().sorted(Comparator.comparingDouble(Geo::getLatitude) //compare latitudes first
                        .thenComparing(Geo::getLongitude).reversed())   //and if equal, compare longitudes
                .map(o -> o.toString()).collect(Collectors.toList());  //map every object into its toString method from Geo class
        return "Penguin{locations=" + list + ", trackID=" + "'"+trackID+"'" + "}";
    }
}
