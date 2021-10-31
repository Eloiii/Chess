package com.example.chess;

public class Pawn implements Piece {

    private final COLOR color;

    public Pawn(COLOR color) {
        this.color = color;
    }

    @Override
    public Cell[] getLegalMoves(Cell from) {
        Cell[] results = new Cell[1];
        results[0] = new Cell(from.getRow() + 1, from.getCol());
        return results;
    }

    @Override
    public char toChar() {
        return 'P';
    }
}
