package com.example.chess;

public class Bishop implements Piece {

    private final COLOR color;

    public Bishop(COLOR color) {
        this.color = color;
    }

    @Override
    public Cell[] getLegalMoves(Cell from) {
        return new Cell[0];
    }

    @Override
    public char toChar() {
        return 'B';
    }
}
