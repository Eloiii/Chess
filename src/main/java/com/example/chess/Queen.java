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
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int row, int col) {
        this.protectedCells = new ArrayList<>();
        boolean checkOnMyKing = Piece.checkOnKing(this.color);
        Board board = Board.getInstance();
        ArrayList<Cell> possibleMoves = getBasicMoves(row, col);
        Piece.filterMoves(checkOnMyKing, board, possibleMoves, isPinned(), this.color);
        return possibleMoves;
    }

    public ArrayList<Cell> getBasicMoves(int row, int col) {
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();
        getBishopMovementsAndProtectedCells(row, col, results);
        getRookMovementsAndProtectedCells(row, col, results);
        return results;
    }

    private void getBishopMovementsAndProtectedCells(int rowFrom, int colFrom, ArrayList<Cell> results) {
        Bishop bishop = new Bishop(this.color);
        ArrayList<Cell> bishopMovements = bishop.getLegalMoves(rowFrom, colFrom);
        this.protectedCells.addAll(bishop.getProtectedCells());
        results.addAll(bishopMovements);
    }

    private void getRookMovementsAndProtectedCells(int rowFrom, int colFrom, ArrayList<Cell> results) {
        Rook rook = new Rook(this.color);
        ArrayList<Cell> rookMovements = rook.getLegalMoves(rowFrom, colFrom);
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

    @Override
    public boolean isPinned() {
        return false;
    }
}
