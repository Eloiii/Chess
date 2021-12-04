package com.example.chess;

import java.util.ArrayList;

public class Rook implements Piece {

    private final COLOR color;
    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private final ArrayList<Cell> protectedCells;

    public Rook(COLOR color) {
        this.color = color;
        this.protectedCells = new ArrayList<>();
    }

    public boolean isPinned() {
        //TODO DETECT IF PINNED
        return false;
    }

    public ArrayList<Cell> getBasicMoves(Cell position) {
        ArrayList<Cell> results = new ArrayList<>();
        // Bas
        for (int rowFor = position.getRow() + 1; rowFor < BoardDimensions.MAX_ROW.getValue(); rowFor++) {
            if (Piece.addMoveAndTestEmptyCell(rowFor, position.getCol(), results, protectedCells, this.color)) break;
        }
        // Haut
        for (int rowFor = position.getRow() - 1; rowFor > -1; rowFor--) {
            if (Piece.addMoveAndTestEmptyCell(rowFor, position.getCol(), results, protectedCells, this.color)) break;
        }
        //Droite
        for (int colFor = position.getCol() + 1; colFor < BoardDimensions.MAX_COL.getValue(); colFor++) {
            if (Piece.addMoveAndTestEmptyCell(position.getRow(), colFor, results, protectedCells, this.color)) break;
        }
        //Gauche
        for (int colFor = position.getCol() - 1; colFor > -1; colFor--) {
            if (Piece.addMoveAndTestEmptyCell(position.getRow(), colFor, results, protectedCells, this.color)) break;
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
