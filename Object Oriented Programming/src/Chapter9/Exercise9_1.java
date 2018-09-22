package Chapter9;

import java.util.Scanner;

public class Exercise9_1 {
    private static Scanner in;
    private static int [][] XO;
    private static boolean winner = false;
    private static int turn;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        initialise();
        printXO();
        int counter = 0;

        while (!winner) {
            System.out.println(convert(turn) + "'s turn");
            int column;
            int row;
            while (true) {
                System.out.print("Select a row on the board: ");
                row = in.nextInt();
                System.out.print("Select a column on the board: ");
                column = in.nextInt();
                if (validate(row, column)) {
                    break;
                } else {
                    System.out.println("Invalid Input. Please input different values.\n");
                }
            }

            XO[row][column] = turn;
            counter++;
            printXO();
            if (checkWin(row, column)) {
                System.out.println(convert(turn) + " Wins!");
                winner = true;
            }
            else {
                if (counter == 9) {
                    System.out.println("Draw");
                    winner = true;
                }
            }
            turn = (turn == 1) ? 0 : 1;
        }
    }

    private static void initialise() {
        turn = 1;
        XO = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                XO[i][j] = -1;
            }
        }
    }

    /**
     * Method returns true if the values of r and c can be written into an array
     * Returns false if values r and c are out of array bounds.
     * Returns false if value in array is already set.
     */
    private static boolean validate(int r, int c) {
        if (r < 0 || r > 2) return false;
        if (c < 0 || c > 2) return false;

        return XO[r][c] == -1;
    }

    private static boolean checkWin(int r, int c) {
        int count = 0;

        for (int i = 0; i < 3; i++) {
            if (XO[r][i] == turn) count++;
        }
        if (count == 3) return true;
        count = 0;

        for (int i = 0; i < 3; i++) {
            if (XO[i][c] == turn) count++;
        }
        if (count == 3) return true;
        count = 0;

        if (r == c) {
            for (int i = 0; i < 3; i++) {
                if (XO[i][i] == turn) count++;
            }
            if (count == 3) return true;
            count = 0;
        }

        if (r + c == 2) {
            for (int i = 0; i < 3; i++) {
                if (XO[i][2 - i] == turn) count++;
            }
            if (count == 3) return true;
        }

        return false;
    }

    private static void printXO() {
        System.out.println("----|---|----");
        System.out.println("| " + convert(XO[0][0]) + " | " + convert(XO[0][1]) + " | " + convert(XO[0][2]) + " |");
        System.out.println("|-----------|");
        System.out.println("| " + convert(XO[1][0]) + " | " + convert(XO[1][1]) + " | " + convert(XO[1][2]) + " |");
        System.out.println("|-----------|");
        System.out.println("| " + convert(XO[2][0]) + " | " + convert(XO[2][1]) + " | " + convert(XO[2][2]) + " |");
        System.out.println("----|---|----");
    }

    private static String convert(int n) {
        switch (n) {
            case 0: return "O";
            case 1: return "X";
            default: return " ";
        }
    }

}