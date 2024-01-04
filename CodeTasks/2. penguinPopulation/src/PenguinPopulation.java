import java.util.Scanner;

// Two young penguins are stranded on an island to grow a new colony. Each penguin's lifespan is 4 years. On the first
// year, there are young and cannot yet reproduce, on the second year, they become adults and each couple produces 4 children.
// On the third year, they are seniors and produce 2 children. On the forth year, they die.
// Our task is to show the population of the colony after n years. (n is an input of the user).
public class PenguinPopulation {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int young,adult,senior,n;
        adult=0;
        senior=0;
        young=2;
        System.out.println("Please insert a number:");
        n = sc.nextInt();
        for(int i=0; i<n; i++){
            senior = adult;
            adult = young;
            young = adult * 2 + senior * 1;
        }
        System.out.println(young+adult+senior);
    }
}