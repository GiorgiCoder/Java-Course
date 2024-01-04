import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReading {


    public static void main(String[] args) {
        processInputFile();
    }
    private static List<PenguinData> instance = null;
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss");


    public static Stream<PenguinData> processInputFile() {
        try{
            return Files.lines(Path.of("data/OC_LPhillips_LittlePenguin_GPS_tracks_DATA.csv"))
                    .skip(1)
                    .map(mapToPenguinData)
                    .filter(x -> x!=null);
        }catch(IOException e){
            System.out.println("Data Path seems to be wrong");
            return null;
        }
    }

    private static Function<String, PenguinData> mapToPenguinData = (line) -> {
        String[] p = line.split(","); // a CSV has comma separated lines
        LocalDateTime dateTime = LocalDateTime.parse(p[2], formatter);
        return new PenguinData(p[0], Integer.parseInt(p[1]), dateTime, Double.parseDouble(p[3]),
                Double.parseDouble(p[4]), p[5], p[6], p[7], new Geo(p[8]));
    };
}