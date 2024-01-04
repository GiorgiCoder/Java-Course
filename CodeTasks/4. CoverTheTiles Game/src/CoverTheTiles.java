import java.util.Scanner;

// Rules:
// This is a two-player dice game. Two player compete each other in every round.
// The respective player roles two dices and computes the sum of the obtained numbers.
// The player roles two dices. Player always rolls two dice and covers two tiles matching the sum of the dice roll.
// For example, if the dice show a 2 and a 3, player may cover one of 2 and 3 or 1 and 4. Selected ones are turned over.
// If no such option is available, the player's account is credited with the total of all open boxes.
// It is not necessary to automatically examine each possibility for the presence of at least one valid combination.
// Game Over:
// There are a total of 10 rounds played, and each round consists of one option made by each player.
// Whoever has the smallest account balance wins.
// The player who successfully closes the final remaining boxes in a round wins instantly,
// regardless of the account values earned.

public class CoverTheTiles extends fop.w3box.MiniJava {
    public static void outputTiles(boolean[] tiles) {
        StringBuilder sb = new StringBuilder("Open tiles: 1:");
        sb.append(tiles[10]);
        for (int i = 1; i < tiles.length; i++) {
            sb.append(" ").append(i + 1).append(":").append(tiles[i]);
        }
        write(sb.toString());
    }
    public static void main(String[] args) throws IllegalAccessException {
        int balance1 = 0;
        int balance2 = 0;
        boolean tiles[] = new boolean[10];
        for (int i = 0; i < tiles.length; i++) {   //setting value of all 10 elements to true
            tiles[i] = true;
        }
        int rounds = 1;
        //start of the game
        while (rounds <= 10) {
            write("Player 1 numbers:");
            int a1 = dice();
            int a2 = dice();
            write(a1);
            write(a2);
            int sum1 = a1 + a2;
            System.out.print("Open tiles:");
            for (int k = 1; k <= 9; k++) {
                System.out.print(" " + k + ":" + tiles[k - 1] + " ");
            }
            System.out.println(" 10:" + tiles[9]);   //writing last element separately so that we start next line
            System.out.println("Which tiles to cover by player 1? (0 for no valid combination)");
            System.out.println("Tile 1:");
            Scanner sc1 = new Scanner(System.in);
            int n1 = sc1.nextInt();
            System.out.println("Tile 2:");
            int n2 = sc1.nextInt();
            //we need to run all the checks to see if user's input is correct
            if(n1<0 || n1>10 || n2<0 || n2>10){   //check if input is in the interval [1;10], if not, ask to input numbers again
                do {
                    System.out.println("Tile 1:");
                    n1 = sc1.nextInt();
                    System.out.println("Tile 2:");
                    n2 = sc1.nextInt();
                }while(n1<1 || n1>10 || n2<1 || n2>10);
            }

            if (n1 == 0 || n2 == 0) {    //if user inputs 0
                for (int p = 0; p <= 9; p++) {
                    if (tiles[p] == true) {
                        balance1++;
                        break;
                    }
                }
            }else {
                if(tiles[n1-1]==false || tiles[n2-1]==false || n1+n2 != sum1 || n1==n2) {   //if user inputs wrong/equal tiles
                    do {
                        if (n1 == 0 || n2 == 0) {    //if user inputs 0 (once more)
                            for (int p = 0; p <= 9; p++) {
                                if (tiles[p] == true) {
                                    balance1++;
                                }
                            }
                        } else {
                            System.out.println("Tile 1:");
                            n1 = sc1.nextInt();
                            System.out.println("Tile 2:");
                            n2 = sc1.nextInt();
                        }
                    }while (tiles[n1 - 1] == false || tiles[n2 = 1] == false || n1+n2 != sum1 || n1 == n2) ;
                }
            }
            if(n1+n2==sum1){   //if everything's correct
                tiles[n1-1]=false;
                tiles[n2-1]=false;
            }
            int count1=0;
            for(int r1=0; r1<=9; r1++){
                if(tiles[r1]==false){    //counting how many "true" elements are in the array
                    count1++;
                }
            }
            if(count1==10){
                System.out.println("Player 1 wins!");  //if count=10, it means that no open tiles are left and player wins
                System.exit(0);
            }
// the end of player 1's movement
            write("Player 2 numbers:");
            int b1 = dice();
            int b2 = dice();
            write(b1);
            write(b2);
            int sum2 = b1 + b2;
            System.out.print("Open tiles:");
            for (int k = 1; k <= 9; k++) {
                System.out.print(" " + k + ":" + tiles[k - 1] + " ");
            }
            System.out.println(" 10:" + tiles[9]);   //writing last element separately so that we start next line
            System.out.println("Which tiles to cover by player 2? (0 for no valid combination)");
            System.out.println("Tile 1:");
            Scanner sc2 = new Scanner(System.in);
            int m1 = sc2.nextInt();
            System.out.println("Tile 2:");
            int m2 = sc2.nextInt();
            if(m1<1 || m1>10 || m2<1 || m2>10){   //check if input is in the interval [1;10], if not, ask to input numbers again
                do {
                    System.out.println("Tile 1:");
                    m1 = sc2.nextInt();
                    System.out.println("Tile 2:");
                    m2 = sc2.nextInt();
                }while(m1<0 || m1>10 || m2<0 || m2>10);
            }
            if (m1 == 0 || m2 == 0) {    //if user inputs 0
                for (int q = 0; q <= 9; q++) {
                    if (tiles[q] == true) {
                        balance2++;
                    }
                }
            }else {
                if(tiles[m1-1]==false || tiles[m2-1]==false || m1+m2 != sum2 || m1==m2){   //if user inputs wrong tiles
                    do {
                        if (m1 == 0 || m2 == 0) {    //if user inputs 0
                            for (int q = 0; q <= 9; q++) {
                                if (tiles[q] == true) {
                                    balance2++;
                                }
                            }
                        } else {
                            System.out.println("Tile 1:");
                            m1 = sc2.nextInt();
                            System.out.println("Tile 2:");
                            m2 = sc2.nextInt();
                        }
                    }while (tiles[m1 - 1] == false || tiles[m2 = 1] == false || m1+m2 != sum2 || m1 == m2) ;
                }
            }
            if(m1+m2==sum2){   //if everything's correct
                tiles[m1-1]=false;
                tiles[m2-1]=false;
            }
            int count2=0;
            for(int r1=0; r1<=9; r1++){
                if(tiles[r1]==false){    //counting how many "true" elements are in the array
                    count2++;
                }
            }
            if(count2==10){
                System.out.println("Player 2 wins!");  //if count=10, it means that no open tiles are left and player wins
                System.exit(0);
            }
            rounds++; //round has been finished
        }
        for (int c = 0; c <= 9; c++) {   //
            if (tiles[c] == true) {   //if any of the elements is true, we count balances
                if (balance1 < balance2) {
                    System.out.println("Player 1 wins!");
                    break;
                } else if (balance1 == balance2) {
                    System.out.println("draw!");
                    break;
                } else if (balance1 > balance2) {
                    System.out.println("Player 2 wins!");
                    break;
                }
            }
        }
    }
}