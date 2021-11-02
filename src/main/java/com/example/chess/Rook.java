package com.example.chess;

import java.util.ArrayList;

public class Rook implements Piece {

    private final COLOR color;

    public Rook(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();

        // Bas
        for (int rowFor = rowFrom + 1; rowFor < BoardDimensions.MAX_ROW.getValue(); rowFor++) {
            if (Piece.cellIsNotVoid(rowFor, colFrom, board, results, this.color)) break;
        }
        // Haut
        for (int rowFor = rowFrom - 1; rowFor > -1; rowFor--) {
            if (Piece.cellIsNotVoid(rowFor, colFrom, board, results, this.color)) break;
        }
        //Droite
        for (int colFor = colFrom + 1; colFor < BoardDimensions.MAX_COL.getValue(); colFor++) {
            if (Piece.cellIsNotVoid(rowFrom, colFor, board, results, this.color)) break;
        }
        //Gauche
        for (int colFor = rowFrom - 1; colFor > -1; colFor--) {
            if (Piece.cellIsNotVoid(rowFrom, colFor, board, results, this.color)) break;
        }
        return results;
    }
    @Override
    public char toChar() {
        return 'R';
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
