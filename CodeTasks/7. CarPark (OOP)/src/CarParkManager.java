public class CarParkManager {

    public static void main(String[] args) {
        LicensePlate plateA;
        plateA = new LicensePlate("GE","AA",17);
        System.out.println(plateA.toString());
        
        LicensePlate plateB = new LicensePlate("GE","AB",420);
        LicensePlate plateC = new LicensePlate("GE","AB",420);

        if(plateA.isEqual(plateB))
            System.out.println("plateA equals plateB");
        else
            System.out.println("plateA doesn't equal plateB");
        if(plateC.isEqual(plateB))
            System.out.println("plateC equals plateB");
        else
            System.out.println("plateC doesn't equal plateB");

        Car carA = null;
        carA = new Car("Tesla",plateA);
        System.out.println(carA.toString());

        System.out.println("carA's brand is " + carA.getBrand());
        carA.setBrand("VolksWagen");
        System.out.println("carA's brand is " + carA.getBrand());

        Car carB = carA;
        System.out.println("'carA' : " + carA.toString());
        System.out.println("'carB' : " + carB.toString());

        carB.setBrand("BMW");
        System.out.println("'carA' : " + carA.toString());
        System.out.println("'carB' : " + carB.toString());

        carB = new Car(carA.getBrand(), plateB);
        System.out.println("'carA' : " + carA.toString());
        System.out.println("'carB' : " + carB.toString());

        CarPark carPark = new CarPark(2);
        carPark.park(carA);
        if(carPark.search(carA.getLicensePlate()) != -1)
            System.out.println("'carA' is in the car park.");
        else
            System.out.println("'carA' is not in the car park.");

        if(carPark.park(carB) != -1)
            System.out.println("new car was parked");
        else
            System.out.println("new car was not parked");

        if(carPark.park(new Car("Vaz",new LicensePlate("GE","ZZ",12))) != -1)
            System.out.println("new car was parked");
        else
            System.out.println("new car was not parked");

    }

}
