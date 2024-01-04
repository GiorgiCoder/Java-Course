public class RgbColor{
    private int red;
    private int green;
    private int blue;
    private int bitDepth;

    public RgbColor(int bitDepth, int red, int green, int blue){
        if(bitDepth < 0 || bitDepth > 31)
            ExceptionUtil.unsupportedOperation("Wrong depth");
        if(red<0 || count(red)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
        if(green<0 || count(green)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
        if(blue<0 || count(blue)>bitDepth)
            ExceptionUtil.unsupportedOperation("Wrong color component");
        this.bitDepth = bitDepth;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void toRgbColor8Bit(){
        if(bitDepth==8){
            new RgbColor8Bit(red,green,blue);
        } else if(bitDepth>8){
            red = red/(IntMath.powerOfTen(bitDepth-8));  // dividing by such power 10 so that numbers become 8 bit
            if(count(red)!=8)
                ExceptionUtil.unsupportedOperation("Wrong color component");
            green = green/(IntMath.powerOfTen(bitDepth-8));
            if(count(green)!=8)
                ExceptionUtil.unsupportedOperation("Wrong color component");
            if(count(blue)!=8)
                blue = blue/(IntMath.powerOfTen(bitDepth-8));
            ExceptionUtil.unsupportedOperation("Wrong color component");
        } else if(bitDepth<8){ //fulfilling numbers to 8 bits
            if(bitDepth==1){
                red = red*11111111;
                green = green*11111111;
                blue = blue*11111111;
                new RgbColor8Bit(red,green,blue);
            }else if(bitDepth==2){
                red=red*1000000+red*10000+red*100+red;
                green=green*1000000+green*10000+green*100+green;
                blue=blue*1000000+blue*10000+blue*100+blue;
                new RgbColor8Bit(red,green,blue);
            }else if(bitDepth==3){
                red=red*100000+red*100+findFirstTwoDigits(red);
                green=green*100000+green*100+findFirstTwoDigits(green);
                blue=blue*100000+blue*100+findFirstTwoDigits(blue);
                new RgbColor8Bit(red,green,blue);
            }else if(bitDepth==4){
                red=red*10000+red;
                green=green*10000+green;
                blue=blue*10000+blue;
                new RgbColor8Bit(red,green,blue);
            }else if(bitDepth==5){
                red=red*1000+findFirstThreeDigits(red);
                green=green*1000+findFirstThreeDigits(green);
                blue=blue*1000+findFirstThreeDigits(blue);
                new RgbColor8Bit(red,green,blue);
            }else if(bitDepth==6){
                red=red*100+findFirstTwoDigits(red);
                green=green*100+findFirstTwoDigits(green);
                blue=blue*100+findFirstTwoDigits(blue);
                new RgbColor8Bit(red,green,blue);
            }else if(bitDepth==7){
                red=red*10+findFirstDigit(red);
                green=green*10+findFirstDigit(green);
                blue=blue*10+findFirstDigit(blue);
            }
        }
    }
    public static int count(int x){  //to count digits in an integer (so we can control bits of ,,binary'' color values
        int count = 0;
        while(x!=0){
            x=x/10;
            count++;
        }
        return count;
    }
    public static int findFirstDigit(int x){   //we will use this when bitDepth < 8
        while(x>9){
            x=x/10;
        }
        return x;
    }
    public static int findFirstTwoDigits(int x){   //we will use this when bitDepth < 8
        while(x>99){
            x=x/10;
        }
        return x;
    }
    public static int findFirstThreeDigits(int x){   //we will use this when bitDepth < 8
        while(x>999){
            x=x/10;
        }
        return x;
    }

    public static void main(String[] args) {
        RgbColor c = new RgbColor(8,10101010,11110000,11110110);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getBitDepth() {
        return bitDepth;
    }
}
