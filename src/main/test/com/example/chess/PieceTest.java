package com.example.chess;

import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class PieceTest {

    @Test
    public void getLegalMoves() throws IllegalMoveException {
        Game game = Game.getInstance();
        game.getBoard().printBoard();

        pawnTests(game);


        System.out.println("___________");
        game.getBoard().printBoard();
    }

    private void pawnTests(Game game) throws IllegalMoveException {
        //single push start
//        pushPawn(game, 2, 5);

        //double push start
//        pushPawn(game, 3, 4);

        //test single push + double
        /*game.getBoard().move(game.getBoard().at(1, 0), 2, 0);
        assertThrows(IllegalMoveException.class, () -> game.getBoard().move(game.getBoard().at(2, 0), 4, 0));*/

        //test double push + single
        /*game.getBoard().move(game.getBoard().at(1, 0), 3, 0);
        assertTrue(game.getBoard().move(game.getBoard().at(3, 0), 4, 0));*/

        //test pawn against pawn
        /*game.getBoard().move(game.getBoard().at(1, 0), 3, 0);
        game.getBoard().move(game.getBoard().at(3, 0), 4, 0);
        assertThrows(IllegalMoveException.class, () -> game.getBoard().move(game.getBoard().at(6, 0), 4, 0));*/

        //test pawn eat
        game.getBoard().move(game.getBoard().at(1, 0), 3, 0);
        game.getBoard().move(game.getBoard().at(3, 0), 4, 0);
        game.getBoard().move(game.getBoard().at(6, 1), 5, 1);
        assertTrue(game.getBoard().move(game.getBoard().at(5, 1), 4, 0));
        assertThrows(IllegalMoveException.class, () -> game.getBoard().move(game.getBoard().at(4, 0), 3, 1));
    }

    private void pushPawn(Game game, int i2, int i3) throws IllegalMoveException {
        // Balck Pawns
        for (int i = 0; i < BoardDimensions.MAX_COL.getValue(); i++) {
            assertTrue(game.getBoard().move(game.getBoard().at(1, i), i2, i));
        }

        // White Pawns
        for (int i = 0; i < BoardDimensions.MAX_COL.getValue(); i++) {
            assertTrue(game.getBoard().move(game.getBoard().at(6, i), i3, i));
        }
    }
}