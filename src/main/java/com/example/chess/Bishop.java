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
        int stop;

        //bas droite
        stop = Math.min(BoardDimensions.MAX_ROW.getValue() - rowFrom, BoardDimensions.MAX_COL.getValue() - colFrom);

        if (colFrom != BoardDimensions.MAX_COL.getValue() - 1) {
            for (int index = rowFrom + 1; index < rowFrom + stop; index++) {
                if (Piece.checkCell(index, colFrom + (index - rowFrom), board, results, this.color)) break;
            }
        }


        //haut gauche
        if (colFrom != 0) {
            for (int index = rowFrom - 1; index > -1; index--) {
                if (Piece.checkCell(index, colFrom + (index - rowFrom), board, results, this.color)) break;
            }
        }


        //bas gauche
        if (colFrom != 0) {
            for (int index = rowFrom + 1; index < Math.abs(colFrom - rowFrom - 1); index++) {
                if (Piece.checkCell(index, colFrom - (index - rowFrom), board, results, this.color)) break;
            }
        }


        //haut droite
//        for (int index = rowFrom - 1; index > -1; index--) {
//            if (Piece.checkCell(index, colFrom - (index - rowFrom), board, results, this.color)) break;
//        }
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
