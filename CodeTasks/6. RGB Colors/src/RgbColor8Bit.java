public class RgbColor8Bit extends RgbColor{
    private int red;
    private int green;
    private int blue;
    private int bitDepth;

    public RgbColor8Bit(int red, int green, int blue){
        super(8, red, green, blue);
        this.bitDepth = 8;
        this.red = red;
        this.green = green;
        this.blue = blue;
        if(red<0 || count(blue)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
        if(green<0 || count(blue)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
        if(blue<0 || count(blue)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
    }

    public static void main(String[] args) {

    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
    }

    @Override
    public int getBlue() {
        return blue;
    }

    @Override
    public int getBitDepth() {
        return bitDepth;
    }
}
