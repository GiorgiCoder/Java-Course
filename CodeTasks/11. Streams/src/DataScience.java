import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        Map<String, List<PenguinData>> groupedMap = stream.collect(Collectors.groupingBy(PenguinData::getTrackID));
        return groupedMap.entrySet().stream().map(b->new Penguin(b.getValue().stream().map(PenguinData::getGeom).toList(),b.getKey()));
    }


    public static void outputPenguinStream() {
        System.out.println(getDataByTrackId(CSVReading.processInputFile()).count());
        Stream<PenguinData> stream = CSVReading.processInputFile();
        getDataByTrackId(stream).sorted(Comparator.comparing(Penguin::getTrackID)).forEach(k-> System.out.println(k.toStringUsingStreams()));
    }

    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        DataScience.getDataByTrackId(stream)
                .sorted(Comparator.comparing(Penguin::getTrackID).reversed())
                .forEach(pingu -> {
                    System.out.println(pingu.getTrackID());
                    List<Double> allLongitudes = pingu.getLocations().stream()
                            .map(x -> x.getLongitude())
                            .collect(Collectors.toList());
                    List<Double> allLatitudes = pingu.getLocations().stream()
                            .map(x -> x.getLatitude())
                            .collect(Collectors.toList());
                    double minLongitude = allLongitudes.stream().mapToDouble(l->l).min().getAsDouble();
                    double maxLongitude = allLongitudes.stream().mapToDouble(l->l).max().getAsDouble();
                    double avgLongitude = allLongitudes.stream().mapToDouble(l->l).average().getAsDouble();
                    double minLatitude = allLatitudes.stream().mapToDouble(l->l).min().getAsDouble();
                    double maxLatitude = allLatitudes.stream().mapToDouble(l->l).max().getAsDouble();
                    double avgLatitude = allLatitudes.stream().mapToDouble(l->l).average().getAsDouble();

                    System.out.println("Min Longitude: " + minLongitude + " Max Longitude: " + maxLongitude + " Avg Longitude: "
                            + avgLongitude + " Min Latitude: " + minLatitude + " Max Latitude: " + maxLatitude + " Avg Latitude: " + avgLatitude);
                });
    }

    public static void main(String[] args) {
//   outputPenguinStream();
    }
}