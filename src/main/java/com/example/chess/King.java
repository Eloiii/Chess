package com.example.chess;

import java.util.ArrayList;

public class King implements Piece {

    private final COLOR color;
    public Cell cellPerformingCheck;
    private boolean isInCheck;

    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private ArrayList<Cell> protectedCells;

    public King(COLOR color) {
        this.color = color;
        this.protectedCells = new ArrayList<>();
        this.isInCheck = false;
    }

    public ArrayList<Cell> getBasicMoves(Cell position) {
        //TODO ROQUE
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();
        if (row != BoardDimensions.MAX_ROW.getValue() - 1) {
            Piece.addMoveAndTestEmptyCell(row + 1, col, results, protectedCells, this.color);
            if (col != BoardDimensions.MAX_COL.getValue() - 1)
                Piece.addMoveAndTestEmptyCell(row + 1, col + 1, results, protectedCells, this.color);
            if (col != 0)
                Piece.addMoveAndTestEmptyCell(row + 1, col - 1, results, protectedCells, this.color);
        }
        if (row != 0) {
            Piece.addMoveAndTestEmptyCell(row - 1, col, results, protectedCells, this.color);
            if (col != BoardDimensions.MAX_COL.getValue() - 1)
                Piece.addMoveAndTestEmptyCell(row - 1, col + 1, results, protectedCells, this.color);
            if (col != 0)
                Piece.addMoveAndTestEmptyCell(row - 1, col - 1, results, protectedCells, this.color);
        }
        if (col != BoardDimensions.MAX_COL.getValue() - 1)
            Piece.addMoveAndTestEmptyCell(row, col + 1, results, protectedCells, this.color);
        if (col != 0)
            Piece.addMoveAndTestEmptyCell(row, col - 1, results, protectedCells, this.color);

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

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public void setCheck(boolean check, Cell piece) {
        this.isInCheck = check;
        if (!check)
            this.cellPerformingCheck = null;
        else
            this.cellPerformingCheck = piece;
    }

    /**
     * Get protected cells
     */
    @Override
    public ArrayList<Cell> getProtectedCells() {
        return this.protectedCells;
    }

    @Override
    public boolean isPinned() {
        return false;
    }

}
