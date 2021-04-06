package com.game;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    private static final int ROWS_ON_BOARD = 6;
    private static final int COLUMNS_ON_BOARD = 7;

    public static void main(String[] args) {
        char[][] connFourBoard = new char[ROWS_ON_BOARD][COLUMNS_ON_BOARD];
        ConnectFour connectFour = new ConnectFour ( connFourBoard,ROWS_ON_BOARD,COLUMNS_ON_BOARD );

        // Print the ConnectFour Board with the initial values.
        connectFour.printGameBoard ();

        boolean flag = true;
        char playerDisc;

        // Initialize the Scanner to
        Scanner sc = new Scanner(System.in);
        int columnNo = 0;
        int rowNo = 0;
        int count=0;
        // Max count for which we need to get the Player1 and Player2's position.
        int maxCount =  ROWS_ON_BOARD * COLUMNS_ON_BOARD;
        while(count<maxCount) {
            System.out.println ();
            if(flag) {
                playerDisc = 'R';
                System.out.print ( "Player 1 [RED] - choose column (1-7): " );
            } else {
                playerDisc = 'G';
                System.out.print ( "Player 2 [GREEN] - choose column (1-7): " );
            }
            try {
                columnNo = sc.nextInt ();
            } catch (InputMismatchException ime) {
                sc.nextLine ();
                System.out.println ("Please enter valid input");
                continue;
            }

            if(columnNo <= 0 && columnNo >COLUMNS_ON_BOARD) {
                System.out.println ("Invalid Column No");
                continue;
            }
            rowNo = connectFour.addDisc ( playerDisc,--columnNo );
            if(rowNo == -1) {
                System.out.println ("Invalid position");
                continue;
            } else {
                connectFour.printGameBoard ();
                if (connectFour.isWinner ( playerDisc, rowNo, columnNo )) {
                    if (flag)
                        System.out.println ( "Player 1 [RED] wins!" );
                    else
                        System.out.println ( "Player 2 [GREEN] wins!" );
                    break;
                }
            }
            flag=!flag;
            count++;
        }
        System.out.println ("Game Over!!!");
    }

}
