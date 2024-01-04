import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Database {
    private static Path baseDataDirectory = Paths.get("data");

    public static void setBaseDataDirectory(Path baseDataDirectory) {
        Database.baseDataDirectory = baseDataDirectory;
    }

    public static Stream<Customer> processInputFileCustomer() {
        try {
            File file = new File(baseDataDirectory.toString() + "//customer.tbl");
            return Files.lines(file.toPath())
                    .map(mapToCustomer);
        }catch(IOException e){
            System.out.println("Data Path seems to be wrong");
            return null;
        }
    }

    private static Function<String, Customer> mapToCustomer = (line) -> {
        String[] c = line.split("\\|");
        return new Customer(Integer.parseInt(c[0]), c[2].toCharArray(), Integer.parseInt(c[3]), c[4].toCharArray(),
                Float.parseFloat(c[5]), c[6], c[7].toCharArray());
    };

    public static Stream<LineItem> processInputFileLineItem() {
        try {
            File file = new File(baseDataDirectory.toString() + "//lineitem.tbl");
            return Files.lines(file.toPath())
                    .map(mapToLineItem);
        }catch(IOException e){
            System.out.println("Data Path seems to be wrong");
            return null;
        }
    }


    private static Function<String, LineItem> mapToLineItem = (line) -> {
        String[] l = line.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new LineItem(Integer.parseInt(l[0]), Integer.parseInt(l[1]), Integer.parseInt(l[2]),
                Integer.parseInt(l[3]), Integer.parseInt(l[4]), Float.parseFloat(l[5]), Float.parseFloat(l[6]),
                Float.parseFloat(l[7]), l[8].charAt(0), l[9].charAt(0), LocalDate.parse(l[10], formatter),
                LocalDate.parse(l[11], formatter), LocalDate.parse(l[12], formatter), l[13].toCharArray(),
                l[14].toCharArray(), l[15].toCharArray());
    };

    public static Stream<Order> processInputFileOrders() {
        try {
            File file = new File(baseDataDirectory.toString() + "//orders.tbl");
            return Files.lines(file.toPath())
                    .map(mapToOrder);
        }catch(IOException e){
            System.out.println("Data Path seems to be wrong");
            return null;
        }
    }

    private static Function<String, Order> mapToOrder = (line) -> {
        String[] o = line.split("\\|");
        DateTimeFormatter orderFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new Order(Integer.parseInt(o[0]), Integer.parseInt(o[1]), o[2].charAt(0),
                Float.parseFloat(o[3]), LocalDate.parse(o[4], orderFormatter), o[5].toCharArray(),
                o[6].toCharArray(), Integer.parseInt(o[7]), o[8].toCharArray());
    };


    public long getAverageQuantityPerMarketSegment(String mktSegment) {  //creating stream which will have only given mktSegment
        Map<Integer, String> custKeyToMarketSegmentMap = processInputFileCustomer()   //mapping custKey -> marketSegment for each costumer
                .collect(Collectors.toMap(Customer::getCustKey, Customer::getMktsegment));

        Map<Integer, String> orderKeyToMarketSegmentMap = processInputFileOrders()    //mapping orderKey -> marketSegment using first mapping
                .collect(Collectors.toMap(Order::getOrderKey, order -> custKeyToMarketSegmentMap.get(order.getCustKey())));
        Stream<LineItem> lineItemsInMktSegment = processInputFileLineItem()
                .filter(lineItem -> mktSegment.equals(orderKeyToMarketSegmentMap.get(lineItem.getOrderKey())));

        double d = lineItemsInMktSegment.mapToLong(LineItem::getQuantity).average().orElse(0) * 100;
        long avgQuantity = ((long) d);

        return avgQuantity;
    }

    public Database() {
        // no need to implement
    }

    public static void main(String[] args) {
        Database db = new Database();
        System.out.println(db.getAverageQuantityPerMarketSegment("AUTOMOBILE"));
        System.out.println(db.getAverageQuantityPerMarketSegment("FURNITURE"));
        System.out.println(db.getAverageQuantityPerMarketSegment("HOUSEHOLD"));
        System.out.println(db.getAverageQuantityPerMarketSegment("BUILDING"));
        System.out.println(db.getAverageQuantityPerMarketSegment("MACHINERY"));
    }
}