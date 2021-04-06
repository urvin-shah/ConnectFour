package com.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectFourTest {

    private ConnectFour connectFour;

    @BeforeEach
    void initBeforeEachMethod() {
        char[][] connFourBoard = new char[6][7];
        connectFour = new ConnectFour ( connFourBoard,6,7 );
    }

    @DisplayName ( "Test Add Disc With Success" )
    @Test
    void testAddDiscSuccess() {
        int rowNum = connectFour.addDisc ( 'R',4 );
        assertEquals ( 0, rowNum);
        assertEquals ( 'R',connectFour.getConnFourBoard ()[0][4] );

        rowNum = connectFour.addDisc ( 'G',4 );
        assertEquals ( 1, rowNum);
        assertEquals ( 'G',connectFour.getConnFourBoard ()[1][4] );
    }

    @DisplayName ( "Test Add Disc With Invalid column test" )
    @Test
    void testAddDiscInvalidColumn() {
        int rowNum = connectFour.addDisc ( 'R',10 );
        assertEquals ( -1, rowNum);

        rowNum = connectFour.addDisc ( 'G',-1 );
        assertEquals ( -1, rowNum);
    }

    @DisplayName ( "Winner with Horizontal Connect" )
    @Test
    void testHorizontalWinner() {
        char[][] horizontalWinner = connectFour.getConnFourBoard ();
        horizontalWinner[0][3] = 'R';
        horizontalWinner[1][3] = 'G';
        horizontalWinner[0][4] = 'R';
        horizontalWinner[1][4] = 'G';
        horizontalWinner[0][5] = 'R';
        horizontalWinner[1][5] = 'G';
        horizontalWinner[0][6] = 'R';
        connectFour.setConnFourBoard ( horizontalWinner );

        assertTrue ( connectFour.isWinner ( 'R',0,6 ) );
    }

    @DisplayName ( "Not Winner with Horizontal Connect" )
    @Test
    void testHorizontalNotYetWinner() {
        char[][] horizontalWinner = connectFour.getConnFourBoard ();
        horizontalWinner[0][3] = 'R';
        horizontalWinner[1][3] = 'G';
        horizontalWinner[0][4] = 'R';
        horizontalWinner[1][4] = 'G';
        horizontalWinner[0][5] = 'R';

        connectFour.setConnFourBoard ( horizontalWinner );

        assertFalse ( connectFour.isWinner ( 'R',0,5 ) );
    }


    @DisplayName ( "Winner with Vertical Connect" )
    @Test
    void testVerticalWinner() {
        char[][] verticalWinner = connectFour.getConnFourBoard ();
        verticalWinner[0][3] = 'G';
        verticalWinner[0][4] = 'R';
        verticalWinner[1][3] = 'G';
        verticalWinner[0][5] = 'R';
        verticalWinner[2][3] = 'G';
        verticalWinner[0][6] = 'R';
        verticalWinner[3][3] = 'G';
        connectFour.setConnFourBoard ( verticalWinner );

        assertTrue ( connectFour.isWinner ( 'G',3,3 ) );
    }

    @DisplayName ( "Not Winner with Vertical Connect" )
    @Test
    void testVerticalNotYetWinner() {
        char[][] verticalWinner = connectFour.getConnFourBoard ();
        verticalWinner[0][3] = 'G';
        verticalWinner[0][4] = 'R';
        verticalWinner[1][3] = 'G';
        verticalWinner[0][5] = 'R';
        verticalWinner[2][3] = 'G';
        connectFour.setConnFourBoard ( verticalWinner );

        assertFalse ( connectFour.isWinner ( 'G',2,3 ) );
    }

    @DisplayName ( "Winner with Forward Digonal Connect" )
    @Test
    void testForwardDiagonalWinner() {
        char[][] forwardDigonalWinner = connectFour.getConnFourBoard ();
        forwardDigonalWinner[0][3] = 'G';
        forwardDigonalWinner[0][4] = 'R';
        forwardDigonalWinner[1][4] = 'G';
        forwardDigonalWinner[0][5] = 'R';
        forwardDigonalWinner[2][5] = 'G';
        forwardDigonalWinner[0][6] = 'R';
        forwardDigonalWinner[3][6] = 'G';
        connectFour.setConnFourBoard ( forwardDigonalWinner );

        assertTrue ( connectFour.isWinner ( 'G',3,6 ) );
    }

    @DisplayName ( "Not Winner with Forward Diagonal Connect" )
    @Test
    void testForwardDigonalNotYetWinner() {
        char[][] verticalWinner = connectFour.getConnFourBoard ();
        verticalWinner[0][3] = 'G';
        verticalWinner[0][4] = 'R';
        verticalWinner[1][4] = 'G';
        verticalWinner[0][5] = 'R';
        verticalWinner[2][5] = 'G';
        connectFour.setConnFourBoard ( verticalWinner );

        assertFalse ( connectFour.isWinner ( 'G',2,5) );
    }

    @DisplayName ( "Winner with Backword Digonal Connect" )
    @Test
    void testBackwordDiagonalWinner() {
        char[][] backwordDigonalWinner = connectFour.getConnFourBoard ();
        backwordDigonalWinner[0][3] = 'G';
        backwordDigonalWinner[0][4] = 'R';
        backwordDigonalWinner[1][2] = 'G';
        backwordDigonalWinner[0][5] = 'R';
        backwordDigonalWinner[2][1] = 'G';
        backwordDigonalWinner[0][6] = 'R';
        backwordDigonalWinner[3][0] = 'G';
        connectFour.setConnFourBoard ( backwordDigonalWinner );

        assertTrue ( connectFour.isWinner ( 'G',3,0 ) );
    }

    @DisplayName ( "Not Winner with Backward Diagonal Connect" )
    @Test
    void testBackwardDigonalNotYetWinner() {
        char[][] verticalWinner = connectFour.getConnFourBoard ();
        verticalWinner[0][3] = 'G';
        verticalWinner[0][4] = 'R';
        verticalWinner[1][2] = 'G';
        verticalWinner[0][5] = 'R';
        verticalWinner[2][1] = 'G';
        connectFour.setConnFourBoard ( verticalWinner );

        assertFalse ( connectFour.isWinner ( 'G',2,1) );
    }

    @Test
    void testPrintGameBoard() throws Exception {
        char[][] horizontalWinner = connectFour.getConnFourBoard ();
        horizontalWinner[0][3] = 'R';
        horizontalWinner[1][3] = 'G';
        horizontalWinner[0][4] = 'R';
        horizontalWinner[1][4] = 'G';
        horizontalWinner[0][5] = 'R';
        horizontalWinner[1][5] = 'G';
        horizontalWinner[0][6] = 'R';
        connectFour.setConnFourBoard ( horizontalWinner );


        StringBuilder sbExpected = new StringBuilder ();
        sbExpected.append ( "| | | | | | | |" ).append ( "\n" );
        sbExpected.append ( "| | | | | | | |" ).append ( "\n" );
        sbExpected.append ( "| | | | | | | |" ).append ( "\n" );
        sbExpected.append ( "| | | | | | | |" ).append ( "\n" );
        sbExpected.append ( "| | | |G|G|G| |" ).append ( "\n" );
        sbExpected.append ( "| | | |R|R|R|R|" ).append ( "\n" );

        String actual = tapSystemOut(()-> connectFour.printGameBoard ());
        assertEquals ( sbExpected.toString (),actual );
    }
}