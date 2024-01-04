
public class Penguin {
    public static int steps = 0;

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                if (j < world[0].length - 1) {
                    if(i==0 && j==1 && world[i][j]==0){
                        System.out.print("L(P)\t");
                    }else if (world[i][j] == 0)
                        System.out.print("L\t");
                } else if (j == world[0].length - 1) {
                    if(i==0 && j==1 && world[i][j]==0){
                        System.out.print("L(P)\t");
                    }else if (world[i][j] == 0)
                        System.out.println("L");
                }
                if (j < world[0].length - 1) {
                    if (world[i][j] == 1)
                        System.out.print("I\t");
                } else if (j == world[0].length - 1) {
                    if (world[i][j] == 1)
                        System.out.println("I");
                }
                if (j < world[0].length - 1) {
                    if (world[i][j] == 2)
                        System.out.print("W(P)\t");
                } else if (j == world[0].length - 1) {
                    if (world[i][j] == 2)
                        System.out.println("W");
                }
                if (j < world[0].length - 1) {
                    if (world[i][j] == 3)
                        System.out.print("S\t");
                } else if (j == world[0].length - 1) {
                    if (world[i][j] == 3)
                        System.out.println("S");
                }
                if (j < world[0].length - 1) {
                    if (world[i][j] == 4)
                        System.out.print("F\t");
                } else if (j == world[0].length - 1) {
                    if (world[i][j] == 4)
                        System.out.println("F");
                }
            }
        }

    }

    public static int[][] generateExampleWorldOne() {
        return new int[][]{{2, 3, 3, 3, 3, 3}, {3, 0, 3, 3, 4, 3}, {3, 3, 3, 3, 3, 1}, {3, 3, 3, 0, 1, 3}, {3, 3, 3, 3, 3, 3}};
    }

    public static int[][] generateExampleWorldTwo() {
        return new int[][]{{0, 0, 0, 2}, {0, 0, 0, 1}, {0, 1, 3, 4}, {3, 4, 3, 3}};
    }
    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        switch (world[pinguRow][pinguColumn]) {
            case 4:
                return true;
            case 3:
                return false;
            case 2:
                if (pinguRow >= 0 && pinguRow < world.length && pinguColumn + 3 >= 0 && pinguColumn + 3 < world[0].length && world[pinguRow][pinguColumn + 3] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow, pinguColumn + 1);
                }else if (pinguRow + 3 >= 0 && pinguRow + 3 < world.length && pinguColumn >= 0 && pinguColumn < world[0].length && world[pinguRow + 3][pinguColumn] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow + 1, pinguColumn);
                }else if (pinguRow - 3 >= 0 && pinguRow - 3 < world.length && pinguColumn >= 0 && pinguColumn < world[0].length && world[pinguRow - 3][pinguColumn] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow - 1, pinguColumn);
                }else if (pinguRow >= 0 && pinguRow < world.length && pinguColumn - 3 >= 0 && pinguColumn - 3 < world[0].length && world[pinguRow][pinguColumn - 3] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow, pinguColumn - 1);
                }
            case 1:
                if (pinguRow - 1 >= 0 && pinguRow - 1 < world.length && pinguColumn - 1 >= 0 && pinguColumn - 1 < world[0].length && world[pinguRow - 1][pinguColumn - 1] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow - 1, pinguColumn - 1);
                }else if (pinguRow - 1 >= 0 && pinguRow - 1 < world.length && pinguColumn + 1 >= 0 && pinguColumn + 1 < world[0].length && world[pinguRow - 1][pinguColumn + 1] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow - 1, pinguColumn + 1);
                }else if (pinguRow + 1 >= 0 && pinguRow + 1 < world.length && pinguColumn - 1 >= 0 && pinguColumn - 1 < world[0].length && world[pinguRow + 1][pinguColumn - 1] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow + 1, pinguColumn - 1);
                }else if (pinguRow + 1 >= 0 && pinguRow - 1 < world.length && pinguColumn + 1 >= 0 && pinguColumn + 1 < world[0].length && world[pinguRow + 1][pinguColumn + 1] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow + 1, pinguColumn + 1);
                }
            case 0:
                if (pinguRow >= 0 && pinguRow < world.length && pinguColumn + 1 >= 0 && pinguColumn + 1 < world[0].length && world[pinguRow][pinguColumn + 1] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow, pinguColumn + 1);
                }else if (pinguRow + 1 >= 0 && pinguRow + 1 < world.length && pinguColumn >= 0 && pinguColumn < world[0].length && world[pinguRow + 1][pinguColumn] != 3){
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow + 1, pinguColumn);
                }else if (pinguRow - 1 >= 0 && pinguRow - 1 < world.length && pinguColumn >= 0 && pinguColumn < world[0].length && world[pinguRow - 1][pinguColumn] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow - 1, pinguColumn);
                }else if (pinguRow >= 0 && pinguRow < world.length && pinguColumn - 1 >= 0 && pinguColumn - 1 < world[0].length && world[pinguRow][pinguColumn - 1] != 3) {
                    world[pinguRow][pinguColumn] = 3;
                    return isFishReachable(world, pinguRow, pinguColumn - 1);
                }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isFishReachable(generateExampleWorldOne(), 0,0));
        System.out.println(isFishReachable(generateExampleWorldTwo(), 0,0));
    }
}