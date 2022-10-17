package src;
import java.util.Scanner;

public class Controller {
    /**
     * Instance fields
     */
    Board game;

    /**
     * Constructor
     */
    public Controller() {
        game = new Board();
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println(" 1 | 2 | 3 \n-----------\n 4 | 5 | 6  \n-----------\n 7 | 8 | 9 ");
        while (true) { // keep looping until break
            if (xPlace()) { // x wins
                break;
            }
            if (checkTie()) {
                System.out.println("No Winner!");
                break;
            }
            if (oPlace()) { // o wins
                break;
            }
        }
        System.out.println("Thank you for playing!");
    }

    private boolean xPlace() {
        System.out.println("Turn: " + (game.getTurns() + 1));
        System.out.println("Player X please enter a empty position (1-9) that you want to place your piece in:");
        Scanner xRead = new Scanner(System.in);
        boolean invalid  = true;
        while (invalid) {
            int xPos = xRead.nextInt();
            invalid = !game.set(xPos, "x"); // set returns true if successfully placed
            if (invalid) {
                System.out.println("Invalid position or position already occupied. Please input a valid position.");
            } else {
                System.out.println("Player X placed a piece in position: " + xPos);
            }
        }
        String currBoard = game.getBoard();
        System.out.println("Current board:\n" + currBoard);
        return checkWinner();
    }

    private boolean oPlace() {
        System.out.println("Turn: " + (game.getTurns() + 1));
        System.out.println("Player O please enter a empty position (1-9) that you want to place your piece in:");
        Scanner oRead = new Scanner(System.in);
        boolean invalid = true;
        while (invalid) {
            int oPos = oRead.nextInt();
            invalid = !game.set(oPos, "o");
            if (invalid) {
                System.out.println("Invalid position or position already occupied. Please input a valid position.");
            } else {
                System.out.println("Player O placed a piece in position: " + oPos);
            }
        }
        String currBoard = game.getBoard();
        System.out.println("Current board:\n" + currBoard);
        return checkWinner();
    }


    private boolean checkWinner() {
        if (game.getTurns() < 5) {
            return false;
        }

        String winner = game.checkWin();

        if (winner.equals("x")) {
            System.out.println("Player X is the winner!");
            return true;
        }

        if (winner.equals("o")) {
            System.out.println("Player O is the winner!");
            return true;
        }

        return false;
    }

    private boolean checkTie() {
        return game.getTurns() >= 9;
    }
}
