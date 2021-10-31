package com.example.chess;

public class King implements Piece {

    private final COLOR color;

    public King(COLOR color) {
        this.color = color;
    }

    @Override
    public Cell[] getLegalMoves(Cell from) {
        return new Cell[0];
    }

    @Override
    public char toChar() {
        return 'K';
    }
}
