import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_BOX = ' ';
    private static final char PLAYER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';
    private char[] board;
    private Scanner scanner;
    private Random random = new Random();
    private byte winner;
    private static final String LINE_SEPARATOR = "-----------";

    public TicTacToeGame() {
        board = new char[BOARD_SIZE];
        scanner = new Scanner(System.in);
        initializeBoard();
        winner = 0;
    }

    public void startGame() {
        showBoardLayout();
        System.out.println("Enter a box number to select. Enjoy!\n");
        boolean isGameOver = false;
        while (!isGameOver) {
            if (checkWinner(PLAYER_MARK)) {
                winner = 1;
                isGameOver = true;
            } else if (!isMoveAvailable()) {
                winner = 3;
                isGameOver = true;
            } else {
                computerMove();
                if (checkWinner(COMPUTER_MARK)) {
                    winner = 2;
                    isGameOver = true;
                }
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_BOX;
        }
    }

    private void showBoardLayout() {
        System.out.println("Board layout:");
        System.out.println("\n 1 | 2 | 3 ");
        System.out.println(LINE_SEPARATOR);
        System.out.println(" 4 | 5 | 6 ");
        System.out.println(LINE_SEPARATOR);
        System.out.println(" 7 | 8 | 9 \n");
    }

    private void displayBoard() {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println(LINE_SEPARATOR);
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println(LINE_SEPARATOR);
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private void playerMove() {
        int move;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt() - 1;
            if (move >= 0 && move < BOARD_SIZE && board[move] == EMPTY_BOX) {
                board[move] = PLAYER_MARK;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private void computerMove() {
        int move;
        while (true) {
            move = random.nextInt(BOARD_SIZE);
            if (board[move] == EMPTY_BOX) {
                board[move] = COMPUTER_MARK;
                break;
            }
        }
    }

    private boolean checkWinner(char mark) {
        return (board[0] == mark && board[1] == mark && board[2] == mark) ||
                (board[3] == mark && board[4] == mark && board[5] == mark) ||
                (board[6] == mark && board[7] == mark && board[8] == mark) ||
                (board[0] == mark && board[3] == mark && board[6] == mark) ||
                (board[1] == mark && board[4] == mark && board[7] == mark) ||
                (board[2] == mark && board[5] == mark && board[8] == mark) ||
                (board[0] == mark && board[4] == mark && board[8] == mark) ||
                (board[2] == mark && board[4] == mark && board[6] == mark);
    }

    private boolean isMoveAvailable() {
        for (char c : board) {
            if (c == EMPTY_BOX) {
                return true;
            }
        }
        return false;
    }

    private void displayResult() {
        switch (winner) {
            case 1:
                System.out.println("You won the game!\nThanks for playing!");
                break;
            case 2:
                System.out.println("You lost the game!\nThanks for playing!");
                break;
            case 3:
                System.out.println("It's a draw!\nThanks for playing!");
                break;
            default:
                break;
        }
    }
}
