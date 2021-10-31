package com.example.chess;

public interface Piece {
    Cell[] getLegalMoves(Cell from);

    char toChar();
}
