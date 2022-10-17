package src;

public class Board {

    /**
     * Instance fields
     */
    private final int ROWS = 3;
    private final int COLS = 3;
    private boolean[][] boolBoard;
    private boolean[][] xBoard;
    private boolean[][] oBoard;
    private int turns;

    public Board() {
        boolBoard = new boolean[ROWS][COLS];
        xBoard = new boolean[ROWS][COLS];
        oBoard = new boolean[ROWS][COLS];
        turns = 0;
    }

    /**
     * Places a player's piece "piece" on position "pos." Guaranteed to be a valid position. Returns true if the
     * action is successful and false otherwise. Can return false if an invalid position is chosen or a piece
     * has already been placed there.
     * @param pos position to place piece
     * @param piece piece to be played, "x" or "o"
     */
    public boolean set(int pos, String piece) {

        int[] posArr = getCoord(pos);

        // check if valid pos
        if (posArr.length == 0) {
            return false;
        }


        int row = posArr[0];
        int col = posArr[1];
        // set piece into board
        boolBoard[row][col] = true;

        // set piece into player board
        if (piece.equals("x")) {
            setX(row, col);
        } else {
            setO(row, col);
        }
        turns += 1;

        return true;
    }

    /**
     * Set piece in xBoard in (row, col) to be true
     * @param row row coordinate
     * @param col column coordinate
     */
    private void setX(int row, int col) {
        xBoard[row][col] = true;
    }

    /**
     * Set piece in oBoard in (row, col) to be true
     * @param row row coordinate
     * @param col column coordinate
     */
    private void setO(int row, int col) {
        oBoard[row][col] = true;
    }

    /**
     * Helper function to get the row-column coordinates of pos.
     * @param pos a position from 1-9 passed in by user
     * @return int array of size 2 containing row value in 0th index and column value in 1st index.
     */
    private int[] getCoord(int pos) {
        // Check if valid position
        if (pos < 1 || pos > 9) {
            return new int[0];
        }

        // Obtain row and column position from pos
        int indexPos = pos - 1;
        int colPos = indexPos % 3;
        int rowPos = 0;
        while (indexPos > 2) {
            indexPos -= 3;
            rowPos += 1;
        }

        // Check if piece has already been placed there
        if (boolBoard[rowPos][colPos]) {
            return new int[0];
        }

        // Valid pos, return array containing rowPos and colPos
        return new int[]{rowPos, colPos};
    }

    /**
     * Returns the current board as a String.
     */
    public String getBoard() {
        String board = "";
        int numDashes = 0;
        for (int row = 0; row < ROWS; row++) {

            // instantiate counters and empty string
            int numDividers = 0;
            String currRow = "";

            for (int col = 0; col < COLS; col++) {
                // Add the piece or not
                if (!boolBoard[row][col]) { // if false, has to be empty
                    currRow += "   ";
                } else if (!xBoard[row][col]) { // boolBoard is true, xBoard or oBoard is true
                    currRow += " o ";
                } else {
                    currRow += " x ";
                }

                // Add the divider
                if (numDividers < 2) { // only 2 dividers needed
                    currRow += "|";
                }
                numDividers += 1;
            }
            // Concatenate currRow to board
            board += currRow + "\n";

            if (numDashes < 2) {
                board += "-----------\n";
            }
            numDashes += 1;
        }
        return board;
    }

    /**
     * Used to check if there's a win
     * @return String "x" or "o" if they won, otherwise ""
     */
    public String checkWin() {
        boolean XWin = checkHoriz("x") || checkVert("x") || checkDiag("x");
        boolean OWin = checkHoriz("o") || checkVert("o") || checkDiag("o");
        if (XWin) {
            return "x";
        } else if (OWin) {
            return "o";
        }
        // no one won
        return "";
    }

    /**
     * Checks for winning condition along rows of the board
     * @param player String of player
     * @return true if player won the game
     */
    private boolean checkHoriz(String player) {
        // check xBoard
        boolean row0, row1, row2;
        if (player.equals("x")) {
            row0 = xBoard[0][0] && xBoard[0][1] && xBoard[0][2];
            row1 = xBoard[1][0] && xBoard[1][1] && xBoard[1][2];
            row2 = xBoard[2][0] && xBoard[2][1] && xBoard[2][2];
            return row0 | row1 | row2;
        }

        // check oBoard
        row0 = oBoard[0][0] && oBoard[0][1] && oBoard[0][2];
        row1 = oBoard[1][0] && oBoard[1][1] && oBoard[1][2];
        row2 = oBoard[2][0] && oBoard[2][1] && oBoard[2][2];
        return row0 | row1 | row2;
    }

    /**
     * Checks for winning condition along columns of the board
     * @param player String of player
     * @return true if player won the game
     */
    private boolean checkVert(String player) {
        // check xBoard
        boolean col0, col1, col2;
        if (player.equals("x")) {
            col0 = xBoard[0][0] && xBoard[1][0] && xBoard[2][0];
            col1 = xBoard[0][1] && xBoard[1][1] && xBoard[2][1];
            col2 = xBoard[0][2] && xBoard[1][2] && xBoard[2][2];
            return col0 | col1 | col2;
        }

        // check oBoard
        col0 = oBoard[0][0] && oBoard[1][0] && oBoard[2][0];
        col1 = oBoard[0][1] && oBoard[1][1] && oBoard[2][1];
        col2 = oBoard[0][2] && oBoard[1][2] && oBoard[2][2];
        return col0 | col1 | col2;
    }

    /**
     * Checks for winning condition along the diagonals of the board
     * @param player String of the player
     * @return true if player won the game
     */
    private boolean checkDiag(String player) {
        boolean diagTLBR, diagTRBL;
        // check xBoard
        if (player.equals("x")) {
            diagTLBR = xBoard[0][0] && xBoard[1][1] && xBoard[2][2];
            diagTRBL = xBoard[0][2] && xBoard[1][1] && xBoard[2][0];
            return diagTLBR || diagTRBL;
        }

        // check oBoard
        diagTLBR = oBoard[0][0] && oBoard[1][1] && oBoard[2][2];
        diagTRBL = oBoard[0][2] && oBoard[1][1] && oBoard[2][0];
        return diagTLBR || diagTRBL;
    }

    /**
     * Gets the current number of turns in the game
     * @return the number of turns in the current game
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Clears all boards and resets the number of turns.
     */
    public void clear() {
        boolBoard = new boolean[ROWS][COLS];
        xBoard = new boolean[ROWS][COLS];
        oBoard = new boolean[ROWS][COLS];
        turns = 0;
    }
}
