public class RgbaColor extends RgbColor{
    private int red;
    private int green;
    private int blue;
    private int bitDepth;
    private int alpha;

    public RgbaColor(int bitDepth, int red, int green, int blue, int alpha){
        super(bitDepth,red,green,blue);
        this.bitDepth = bitDepth;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        if(bitDepth < 0 || bitDepth > 31)
            ExceptionUtil.unsupportedOperation("Wrong depth");
        if(red<0 || count(red)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
        if(green<0 || count(green)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
        if(blue<0 || count(blue)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
        if(alpha<0 || count(alpha)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong alpha component");
    }

    public int getAlpha() {
        return alpha;
    }
}
