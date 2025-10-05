import java.util.*;

public class TicTacToeNormal {

    enum Mode {
        HUMAN, AI;

        static Mode fromString(String mode) {
            if (mode == null)
                throw new IllegalArgumentException("null mode");
            if (mode.equals("h"))
                return Mode.HUMAN;
            if (mode.equals("a"))
                return Mode.AI;

            throw new IllegalArgumentException("unknown mode " + mode);
        }
    }

    static int rows = 3;
    static int column = 3;
    static int moveCount = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int scoreX = 0;
        int scoreO = 0;
        int drawTimes = 0;
        int lowBound = 1;
        int highBound = 9;
        // int n = 1;

        while (playAgain) {

            System.out.println("Choose the mode (human vs. human (h) / human vs. AI (a))");
            Mode mode;
            try {
                mode = Mode.fromString(scanner.next().trim().toLowerCase());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage() + "defaulting to human vs. AI");
                mode = Mode.AI;
            }

            moveCount = 0;
            char currentplayer = 'X';

            char board[][] = new char[rows][column];

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    // char c = (char) ('0' + n);
                    board[i][j] = ' ';
                    // n++;
                }
            }

            printBoard(board);

            while (true) {

                try {

                    if (isAiTurn(mode, currentplayer)) {
                        int pos = getComputerMove(board);

                        System.out.println("The computer chose position " + pos + ". Your turn now");

                        int row = (pos - 1) / 3;
                        int col = (pos - 1) % 3;

                        board[row][col] = currentplayer;

                    } else {

                        System.out.println("Player (" + currentplayer
                                + ") , Choose a position from 1-9 (1 is top left and 9 is bottom right)");

                        int position = scanner.nextInt();

                        if (position < lowBound || position > highBound) {
                            System.out.println("Out of Range, Choose a number from 1-9");
                            continue;
                        }

                        int row = (position - 1) / 3;
                        int col = (position - 1) % 3;

                        if (board[row][col] == 'X' || board[row][col] == 'O') {
                            System.out.println("That spot is taken, choose another spot");
                            continue;
                        } else {

                            board[row][col] = currentplayer; // compute position and place currentplayer in that cell
                        }
                    }

                    // check if the player won
                    if (checkWinAfterMove(board)) {
                        printBoard(board);
                        System.out.println("player " + currentplayer + " won the game ");
                        if (currentplayer == 'X') {
                            scoreX++;
                        } else if (currentplayer == 'O') {
                            scoreO++;
                        }
                        printScores(scoreX, scoreO, drawTimes);
                        break;
                    }

                    moveCount++;

                    // check if it is a draw
                    if (isBoardFull(board)) {
                        printBoard(board);
                        System.out.println("Draw, the game is over!");
                        drawTimes++;
                        printScores(scoreX, scoreO, drawTimes);
                        break;
                    }
                    // swap the player by creating a placeholder to prevent overwritting
                    currentplayer = (currentplayer == 'X') ? 'O' : 'X';

                    printBoard(board);

                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number from 1-9");
                    scanner.nextLine();
                }

            }
            playAgain = askPlayAgain(scanner);

        }

    }

    // end of main method
    // helper functions

    // print board
    private static void printBoard(char[][] board) {

        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.printf(" %c | %c | %c %n", board[i][0], board[i][1], board[i][2]);
            if (i < 2) {
                System.out.println("---*---*---");

            }
        }
        System.out.println();
    }

    // check if this move is a win
    private static boolean checkWinAfterMove(char[][] board) {
        // row 1
        if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') {
            return true;
        } else if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') {
            return true;
        } // row 2
        else if (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') {
            return true;
        } else if (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') {
            return true;
        } // row 3
        else if (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') {
            return true;
        } else if (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') {
            return true;
        } // column 1
        else if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') {
            return true;
        } else if (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') {
            return true;
        } // column 2
        else if (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') {
            return true;
        } else if (board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O') {
            return true;
        } // column 3
        else if (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') {
            return true;
        } else if (board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') {
            return true;
        } // diagonal 1
        else if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
            return true;
        } else if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
            return true;
        } // diagonal 2 (antidiagonal)
        else if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
            return true;
        } else if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
            return true;
        }
        return false;
    }

    // draw?
    private static boolean isBoardFull(char[][] board) {
        if (moveCount == 9) {
            return true;
        }
        return false;
    }

    // playagain?
    private static boolean askPlayAgain(Scanner scanner) {
        while (true) {
            System.out.println("Do you want to play again (yes / no)");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("yes")) {
                return true;
            }
            if (answer.equalsIgnoreCase("no")) {
                System.out.println("Thank you for playing my game");
                return false;
            }
            System.out.println("Invalid input, enter (yes / no)");
        }

    }

    // print scores
    private static void printScores(int scoreX, int scoreO, int drawTimes) {
        System.out.println("Player (X) has score: " + scoreX + ", Player (O) has score: " + scoreO
                + ", Draw times are: " + drawTimes);
    }

    // return a valid position for the computer
    private static int getComputerMove(char[][] board) {

        int slotNumber = 1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return slotNumber;
                }

                slotNumber++;
            }
        }
        return -1;
    }

    // get AI turn
    private static boolean isAiTurn(Mode mode, char currentplayer) {
        if (mode == Mode.AI && currentplayer == 'O') {
            return true;
        }
        return false;
    }

}
