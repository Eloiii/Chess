package com.example.chess;

import java.util.ArrayList;

public class King implements Piece {

    private final COLOR color;
    public Cell cellPerformsCheck;
    private boolean isCheck;

    public King(COLOR color) {
        this.color = color;
        this.isCheck = false;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();
        if (rowFrom != BoardDimensions.MAX_ROW.getValue() - 1) {
            Piece.addIfLegalDestination(rowFrom + 1, colFrom, board, results, this.color);
            if (colFrom != BoardDimensions.MAX_COL.getValue() - 1)
                Piece.addIfLegalDestination(rowFrom + 1, colFrom + 1, board, results, this.color);
            if (colFrom != 0)
                Piece.addIfLegalDestination(rowFrom + 1, colFrom - 1, board, results, this.color);
        }
        if (rowFrom != 0) {
            Piece.addIfLegalDestination(rowFrom - 1, colFrom, board, results, this.color);
            if (colFrom != BoardDimensions.MAX_COL.getValue() - 1)
                Piece.addIfLegalDestination(rowFrom - 1, colFrom + 1, board, results, this.color);
            if (colFrom != 0)
                Piece.addIfLegalDestination(rowFrom - 1, colFrom - 1, board, results, this.color);
        }
        if (colFrom != BoardDimensions.MAX_COL.getValue() - 1)
            Piece.addIfLegalDestination(rowFrom, colFrom + 1, board, results, this.color);
        if (colFrom != 0)
            Piece.addIfLegalDestination(rowFrom, colFrom - 1, board, results, this.color);

        return results;
    }

    @Override
    public char toChar() {
        return 'K';
    }

    @Override
    public boolean isVoidPiece() {
        return false;
    }

    @Override
    public COLOR getColor() {
        return this.color;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean check, Cell piece) {
        this.isCheck = check;
        if (!check)
            this.cellPerformsCheck = null;
        else
            this.cellPerformsCheck = piece;
    }
}
