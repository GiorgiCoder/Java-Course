public class Car {
    private String brand;
    private LicensePlate licensePlate;
    private int chassisNumber;

    private static int count = 0;

    public Car(String brand, LicensePlate licensePlate) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.chassisNumber = count++;
    }

    public String toString() {
        return "Car " + chassisNumber + ": (Brand: " + brand + ", License Plate: " + licensePlate + ")";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getChassisNumber() {
        return chassisNumber;
    }
}