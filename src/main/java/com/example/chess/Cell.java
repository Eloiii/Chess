package com.example.chess;

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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
