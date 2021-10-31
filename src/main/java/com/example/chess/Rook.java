package com.example.chess;

public class Rook implements Piece {

    private final COLOR color;

    public Rook(COLOR color) {
        this.color = color;
    }

    @Override
    public Cell[] getLegalMoves(Cell from) {
        return new Cell[0];
    }

    @Override
    public char toChar() {
        return 'R';
    }
}
