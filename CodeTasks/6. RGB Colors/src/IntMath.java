/**
 * Pre-defined class! No grading inside this class!!!
 * <p>
 * Please do not modify!
 */
public class IntMath {
    private IntMath() {

    }

    public static int powerOfTwo(int exponent) {
        if (exponent > 31)
            ExceptionUtil.unsupportedOperation("exponent too large: " + exponent);
        if (exponent < 0)
            ExceptionUtil.unsupportedOperation("exponent cannot be negative: " + exponent);
        int result = 1;
        while(exponent!=0){
            result = result * 2;
            exponent--;
        }
        return result;
    }

    public static int powerOfTen(int exponent) {
        if (exponent > 31)
            ExceptionUtil.unsupportedOperation("exponent too large: " + exponent);
        if (exponent < 0)
            ExceptionUtil.unsupportedOperation("exponent cannot be negative: " + exponent);
        int result = 1;
        while(exponent!=0){
            result = result * 10;
            exponent--;
        }
        return result;
    }
}
