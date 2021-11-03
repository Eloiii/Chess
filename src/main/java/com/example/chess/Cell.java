package com.example.chess;

import java.util.ArrayList;

public class Cell {
    private final int row;
    private final int col;
    private Piece piece;

    public Cell(int x, int y) {
        this.row = x;
        this.col = y;
        this.piece = new VoidPiece();
    }

    public Cell(int x, int y, Piece piece) {
        this.row = x;
        this.col = y;
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int distanceFrom(Cell to) {
        int diffRow = to.getRow() - this.getRow();
        int diffCol = to.getCol() - this.getCol();
        return Math.abs(Math.max(diffCol, diffRow));
    }

    public boolean isEmptyCell() {
        return this.piece.isVoidPiece();
    }

    public String toString() {
        return this.piece.toChar() + " at [" + this.row + "," + this.col + "]";
    }

    public ArrayList<Cell> getLegalMovesForPiece(Board board) {
        return this.piece.getLegalMoves(this.row, this.col, board);
    }
}
