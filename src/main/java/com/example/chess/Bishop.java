package com.example.chess;

import java.util.ArrayList;

public class Bishop implements Piece {

    private final COLOR color;

    public Bishop(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();

        //diagonal bas droite
        for (int rowIndex = rowFrom; rowIndex < BoardDimensions.MAX_ROW.getValue(); rowIndex++) {
            if (Piece.checkCell(colFrom + rowIndex, rowFrom + rowIndex, board, results, this.color)) break;
        }

        return results;
    }

    @Override
    public char toChar() {
        return 'B';
    }

    @Override
    public boolean isVoidPiece() {
        return false;
    }

    @Override
    public COLOR getColor() {
        return this.color;
    }
}
