import java.util.*;

public class TicTacToe {

    //fields
    private static char[][] array = new char[3][3];
    private static int countX = 0;
    private static int countO = 0;
    private static int countSpace = 0;
    private static int rowX = 0;
    private static int rowO = 0;
    private static boolean rightCoordinates = false;
    private static int coordinateFirst = 0;
    private static int coordinateSecond = 0;
    private static Scanner sc = new Scanner(System.in);
    private static boolean firstTime = true;
    private static char gameTurn = 'X';
    private static String gameState = " ";

    public static void main(String[] args) {
        while (!gameState.equals("Draw") && !gameState.equals("X wins") && !gameState.equals("O wins")) {
            showGrid();
            getInput();
            analyze();
        }
        showGrid();
        System.out.println(gameState);
    }

    public static void showGrid() {
        if (firstTime) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = ' ';
                }
            }
            firstTime = false;
        }
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            System.out.print(" " + array[i][0] + " " + array[i][1] + " " + array[i][2] + " ");
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void analyze() {
        //count elements
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 'X') {
                    countX++;
                } else if (array[i][j] == 'O') {
                    countO++;
                } else if (array[i][j] == ' ') {
                    countSpace++;
                }
            }
        }

        /*
         * 0 1 2
         * 3 4 5
         * 6 7 8
         *
         * count rows
         */
        int[] rows = {
                array[0][0] + array[0][1] + array[0][2],
                array[1][0] + array[1][1] + array[1][2],
                array[2][0] + array[2][1] + array[2][2],
                array[0][0] + array[1][0] + array[2][0],
                array[0][1] + array[1][1] + array[2][1],
                array[0][2] + array[1][2] + array[2][2],
                array[0][0] + array[1][1] + array[2][2],
                array[2][0] + array[1][1] + array[0][2]
        };

        for (int i = 0; i < 8; i++) {
            if (rows[i] == 'X' * 3) {
                rowX++;
            }
            if (rows[i] == 'O' * 3) {
                rowO++;
            }
        }

        if (countSpace == 0 && rowO == 0 && rowX == 0) {
            gameState = "Draw";
        }
        if (rowX == 1 && rowO == 0) {
            gameState = "X wins";
        }
        if (rowO == 1 && rowX == 0) {
            gameState = "O wins";
        }
    }

    public static void getInput() {
        do {
            System.out.println("Enter the coordinates: ");
            try {
                coordinateFirst = sc.nextInt();
                coordinateSecond = sc.nextInt();
                if (!(coordinateFirst > 0 && coordinateFirst < 4 && coordinateSecond > 0 && coordinateSecond < 4)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    rightCoordinates = false;
                    continue;
                } else if (array[coordinateFirst - 1][coordinateSecond - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    rightCoordinates = false;
                    continue;
                }

                array[coordinateFirst - 1][coordinateSecond - 1] = 'X';
                rightCoordinates = true;
            } catch (InputMismatchException exception) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
                rightCoordinates = false;
            }

        } while (!rightCoordinates);

        if (gameTurn == 'X') {
            array[coordinateFirst - 1][coordinateSecond - 1] = 'X';
            gameTurn = 'O';
        } else {
            array[coordinateFirst - 1][coordinateSecond - 1] = 'O';
            gameTurn = 'X';
        }

    }

}
