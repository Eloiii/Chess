package com.example.chess;

public class Queen implements Piece{

    private final COLOR color;

    public Queen(COLOR color) {
        this.color = color;
    }

    @Override
    public Cell[] getLegalMoves(Cell from) {
        return new Cell[0];
    }

    @Override
    public char toChar() {
        return 'Q';
    }
}
