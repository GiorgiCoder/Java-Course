import java.util.Scanner;

// user writes a number n. We want to crush it to 1 by these operations depending on i, which is the remainder of n/5.
// 1. When i=0, divide n by 5. 2. When i=1, add 9. 3. When i=2, subtract 1. 4. When i=3, add 7. 5. When i=4, add 6.
// We output the number of operations it takes to crush n to 1 (or <=1)
public class GiveFive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please insert a number:");
        int n = sc.nextInt();
        int count = 0;
        int i;
        while (n > 1) {
            i = n % 5;
            count++;
            if (i == 0)
                n = n / 5;
            else if (i == 1)
                n += 9;
            else if (i == 2)
                n -= 1;
            else if (i == 3)
                n += 7;
            else
                n += 6;
        }
        System.out.println(count);
    }
}
