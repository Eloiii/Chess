package com.example.chess;

import java.util.ArrayList;

public class Rook implements Piece {

    private final COLOR color;
    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private ArrayList<Cell> protectedCells;

    public Rook(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom) {
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();

        // Bas
        for (int rowFor = rowFrom + 1; rowFor < BoardDimensions.MAX_ROW.getValue(); rowFor++) {
            if (Piece.addMoveAndTestEmptyCell(rowFor, colFrom, results, protectedCells, this.color)) break;
        }
        // Haut
        for (int rowFor = rowFrom - 1; rowFor > -1; rowFor--) {
            if (Piece.addMoveAndTestEmptyCell(rowFor, colFrom, results, protectedCells, this.color)) break;
        }
        //Droite
        for (int colFor = colFrom + 1; colFor < BoardDimensions.MAX_COL.getValue(); colFor++) {
            if (Piece.addMoveAndTestEmptyCell(rowFrom, colFor, results, protectedCells, this.color)) break;
        }
        //Gauche
        for (int colFor = colFrom - 1; colFor > -1; colFor--) {
            if (Piece.addMoveAndTestEmptyCell(rowFrom, colFor, results, protectedCells, this.color)) break;
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

    /**
     * Get protected cells
     */
    @Override
    public ArrayList<Cell> getProtectedCells() {
        return this.protectedCells;
    }
}
