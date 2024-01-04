import java.util.Scanner;

public class Main {

    public static void matrix() {
        System.out.println("Enter dimensions in the format [rows]x[columns]: \n");
        Scanner sc = new Scanner(System.in);
        String[] str = sc.next().split("x");
        int r = Integer.parseInt(str[0]);
        int c = Integer.parseInt(str[1]);
        System.out.println("Fill in the matrix:");
        double[][] matrix = new double[r][c];
        int rows = matrix.length;
        int columns = matrix[0].length;
        Scanner scanner = new Scanner(System.in);
        int i, j;

        for(i = 0; i < rows; ++i) {
            for(j = 0; j < columns; ++j) {
                System.out.print("Enter a value for position [" + i + "][" + j + "]: ");
                matrix[i][j] = Double.parseDouble(scanner.nextLine());
            }
        }

        System.out.println("Here's your matrix: ");

        for(i = 0; i < matrix.length; ++i) {
            for(j = 0; j < matrix[i].length; ++j) {
                System.out.print("[" + (int)matrix[i][j] + "] ");
            }

            System.out.println();
        }

        System.out.println("Enter 'pls' to get the information: ");
        String s = sc.next();
        if (s.equals("pls")) {
            for(i = 0; i < matrix.length; ++i) {
                for(j = 0; j < matrix[i].length; ++j) {
                    System.out.printf("[%.2f] ", reduce(matrix)[i][j]);
                }

                System.out.println();
            }

            rank(reduce(matrix));
        } else {
            System.out.println("No such command available");
        }
    }

    public static void rank(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int rank = 0;

        for(int row = 0; row < rows; ++row) {
            for(int col = 0; col < columns; ++col) {
                if (matrix[row][col] == 1.0) {
                    boolean foundLeadingOne = true;

                    for(int i = 0; i < col; ++i) {
                        if (matrix[row][i] != 0.0) {
                            foundLeadingOne = false;
                            break;
                        }
                    }

                    if (foundLeadingOne) {
                        ++rank;
                        break;
                    }
                }
            }
        }

        System.out.println("The rank of the matrix is: " + rank);
    }

    public static double[][] reduce(double[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        double[][] rrefMatrix = new double[rowCount][colCount];

        int lead;
        for(lead = 0; lead < rowCount; ++lead) {
            System.arraycopy(matrix[lead], 0, rrefMatrix[lead], 0, colCount);
        }

        lead = 0;

        int row;
        int j;
        for(row = 0; row < rowCount && colCount > lead; ++row) {
            j = row;

            while(Math.abs(rrefMatrix[j][lead]) < 1.0E-6) {
                ++j;
                if (rowCount == j) {
                    j = row;
                    ++lead;
                    if (colCount == lead) {
                        return rrefMatrix;
                    }
                }
            }

            double[] temp = rrefMatrix[j];
            rrefMatrix[j] = rrefMatrix[row];
            rrefMatrix[row] = temp;
            double lv = rrefMatrix[row][lead];

            int iRow;
            for(iRow = 0; iRow < colCount; ++iRow) {
                rrefMatrix[row][iRow] /= lv;
            }

            for(iRow = 0; iRow < rowCount; ++iRow) {
                if (iRow != row) {
                    double mult = rrefMatrix[iRow][lead];

                    for(j = 0; j < colCount; ++j) {
                        rrefMatrix[iRow][j] -= mult * rrefMatrix[row][j];
                    }
                }
            }

            ++lead;
        }

        for(row = 0; row < rowCount; ++row) {
            for(j = 0; j < colCount; ++j) {
                if (Math.abs(rrefMatrix[row][j]) < 1.0E-6) {
                    rrefMatrix[row][j] = 0.0;
                }
            }
        }

        return rrefMatrix;
    }

    public static void main(String[] args) {
        matrix();
    }
}
