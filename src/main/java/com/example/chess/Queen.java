package com.example.chess;

import java.util.ArrayList;

public class Queen implements Piece {

    private final COLOR color;
    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private ArrayList<Cell> protectedCells;

    public Queen(COLOR color) {
        this.color = color;
        this.protectedCells = new ArrayList<>();
    }

    public ArrayList<Cell> getBasicMoves(Cell position) {
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();
        getBishopMovementsAndProtectedCells(position, results);
        getRookMovementsAndProtectedCells(position, results);
        return results;
    }

    private void getBishopMovementsAndProtectedCells(Cell source, ArrayList<Cell> results) {
        Bishop bishop = new Bishop(this.color);
        ArrayList<Cell> bishopMovements = Piece.getLegalMoves(source, bishop);
        this.protectedCells.addAll(bishop.getProtectedCells());
        results.addAll(bishopMovements);
    }

    private void getRookMovementsAndProtectedCells(Cell source, ArrayList<Cell> results) {
        Rook rook = new Rook(this.color);
        ArrayList<Cell> rookMovements = Piece.getLegalMoves(source, new Rook(this.color));
        this.protectedCells.addAll(rook.getProtectedCells());
        results.addAll(rookMovements);
    }

    @Override
    public char toChar() {
        return 'Q';
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
