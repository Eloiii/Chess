package com.example.chess;

import java.util.ArrayList;

public class King implements Piece {

    private final COLOR color;
    public Cell cellPerformsCheck;
    private boolean isCheck;

    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private ArrayList<Cell> protectedCells;

    public King(COLOR color) {
        this.color = color;
        this.isCheck = false;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom) {
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();
        if (rowFrom != BoardDimensions.MAX_ROW.getValue() - 1) {
            Piece.addMoveAndTestEmptyCell(rowFrom + 1, colFrom, results, protectedCells, this.color);
            if (colFrom != BoardDimensions.MAX_COL.getValue() - 1)
                Piece.addMoveAndTestEmptyCell(rowFrom + 1, colFrom + 1, results, protectedCells, this.color);
            if (colFrom != 0)
                Piece.addMoveAndTestEmptyCell(rowFrom + 1, colFrom - 1, results, protectedCells, this.color);
        }
        if (rowFrom != 0) {
            Piece.addMoveAndTestEmptyCell(rowFrom - 1, colFrom, results, protectedCells, this.color);
            if (colFrom != BoardDimensions.MAX_COL.getValue() - 1)
                Piece.addMoveAndTestEmptyCell(rowFrom - 1, colFrom + 1, results, protectedCells, this.color);
            if (colFrom != 0)
                Piece.addMoveAndTestEmptyCell(rowFrom - 1, colFrom - 1, results, protectedCells, this.color);
        }
        if (colFrom != BoardDimensions.MAX_COL.getValue() - 1)
            Piece.addMoveAndTestEmptyCell(rowFrom, colFrom + 1, results, protectedCells, this.color);
        if (colFrom != 0)
            Piece.addMoveAndTestEmptyCell(rowFrom, colFrom - 1, results, protectedCells, this.color);

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

    /**
     * Get protected cells
     */
    @Override
    public ArrayList<Cell> getProtectedCells() {
        return this.protectedCells;
    }
}
