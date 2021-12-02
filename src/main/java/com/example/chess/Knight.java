package com.example.chess;

import java.util.ArrayList;

public class Knight implements Piece {

    private static final int[][] possibleCombinations = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    private final COLOR color;
    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private ArrayList<Cell> protectedCells;

    public Knight(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom) {
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();
        for (int[] combination :
                possibleCombinations) {
            int rowTo = rowFrom + combination[0];
            int colTo = colFrom + combination[1];
            if (rowTo >= 0 && rowTo < BoardDimensions.MAX_ROW.getValue() && colTo >= 0 && colTo < BoardDimensions.MAX_COL.getValue()) {
                Piece.addMoveAndTestEmptyCell(rowTo, colTo, results, protectedCells, this.color);
            }
        }
        return results;
    }

    @Override
    public char toChar() {
        return 'N';
    }

    @Override
    public boolean isVoidPiece() {
        return false;
    }

    @Override
    public COLOR getColor() {
        return this.color;
    }

    /**
     * Get protected cells
     */
    @Override
    public ArrayList<Cell> getProtectedCells() {
        return this.protectedCells;
    }
}
