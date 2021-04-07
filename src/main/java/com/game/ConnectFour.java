package com.game;

/**
 * Connect Four is game of 2 players, which throws R or G disc one after another.
 * 1. The vertical board is composed of seven columns and six rows. Initially, all positions are empty.
 * 2. Players introduce discs at the top of the columns. The disc falls to the bottom of the column if the column is empty. Future discs introduced to the same column will stack over previous ones.
 * 3. It is a two-person game. Player 1 uses red ('R') and Player 2 uses green ('G'). Players take alternate turns, inserting one disc each time.
 * 4. When no more discs can be inserted, the game finishes, and it is considered a draw.
 * 5. If a player inserts a disc and connects more than three discs of his color in a straight vertical, horizontal or diagonal line, then that player wins.
 */
public class ConnectFour {

    private static final int WINNER_MAX_ROW_INC = 3;
    private static final int WINNER_MAX_COL_INC = 4;
    private int noOfRows;
    private int noOfColumns;

    // Connect4 board
    private char[][] connFourBoard;

    public ConnectFour(char[][] connFourBoard,int noOfRows,int noOfColumns) {
        this.connFourBoard = connFourBoard;
        this.noOfRows = noOfRows;
        this.noOfColumns = noOfColumns;
    }

    /**
     * This method will be called when a new disc has been thrown by player with given column no.
     * It will update the ConnectFourBoard with the given column and it will return the row which gets impacted.
     * If its a valid row it will return the appropriate row number.
     * If all rows with the given column are full then it will return -1, to indicate that given input column is invalid.
     * @param disc
     * @param columnNo
     * @return
     */
    public int addDisc(char disc,int columnNo) {
        if(!(columnNo <0 || columnNo > this.noOfColumns)) {
            for(int i=0;i<this.noOfRows;i++) {
                if(connFourBoard[i][columnNo] == '\u0000' ) {
                    connFourBoard[i][columnNo] = disc;
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * This method will be called after player thrown the respective disc, this method will decide weather player win or not.
     * To determine the winning situation we need to check horizontally, Vertically, Forward/Backward diagonally consecutive 4 discs of the same player.
     * If 4 consecutive discs found horizontally, Vertically or diagonally then this method will return true, else it will return false.
     * @param player
     * @param rowPos
     * @param colPos
     * @return
     */
    public boolean isWinner(char player,int rowPos,int colPos) {
        // Horizontal check for winning
        if(connFourBoard[rowPos][3] == player) {
            for (int col = 0; col < WINNER_MAX_COL_INC; col++) {
                if(connFourBoard[rowPos][col] == player &&
                   connFourBoard[rowPos][col+1] == player &&
                        connFourBoard[rowPos][col+2] == player &&
                        connFourBoard[rowPos][col+3] == player) {
                    return true;
                }
            }
        }

        // Vertical Check for winning
        for(int row=0;row < WINNER_MAX_ROW_INC;row++) {
            if(connFourBoard[row][colPos] == player &&
                connFourBoard[row+1][colPos] == player &&
                connFourBoard[row+2][colPos] == player &&
                connFourBoard[row+3][colPos] == player) {
                return true;
            }
        }

        // Forward diagonal check
        for(int row=0;row<WINNER_MAX_ROW_INC;row++) {
            for(int col=0;col<WINNER_MAX_COL_INC;col++) {
                if(connFourBoard[row][col] == player &&
                        connFourBoard[row+1][col+1] == player &&
                        connFourBoard[row+2][col+2] == player &&
                        connFourBoard[row+3][col+3] == player) {
                    return true;
                }
            }
        }

        // Backward Diagonal check
        for(int row=0;row<WINNER_MAX_ROW_INC;row++) {
            for(int col=3;col<this.noOfColumns;col++) {
                if(connFourBoard[row][col] == player &&
                        connFourBoard[row+1][col-1] == player &&
                        connFourBoard[row+2][col-2] == player &&
                        connFourBoard[row+3][col-3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method will Draw the ConnectFour board after each disc thrown, it will draw the player's disc position on
     * the Connect Four Board.
     */
    public void printGameBoard() {
        StringBuilder sbBoard = new StringBuilder ();
        for(int row=this.noOfRows-1;row>=0;row--) {
            sbBoard.append ( "|" );
            for(int col=0;col<this.noOfColumns;col++) {
                sbBoard.append ( (connFourBoard[row][col] == '\u0000')?" ":connFourBoard[row][col] ).append ( "|" );
            }
            sbBoard.append ( "\n" );
        }
        System.out.print (sbBoard.toString ());
    }

    public char[][] getConnFourBoard() {
        return connFourBoard;
    }

    public void setConnFourBoard(char[][] connFourBoard) {
        this.connFourBoard = connFourBoard;
    }
}
