package com.example.chess;

public class VoidPiece implements Piece{
    public String toString() {
        return "";
    }

    @Override
    public Cell[] getLegalMoves(Cell from) {
        return new Cell[0];
    }

    @Override
    public char toChar() {
        return 0;
    }
}
