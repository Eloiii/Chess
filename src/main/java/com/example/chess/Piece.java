package com.example.chess;

import java.util.ArrayList;

public interface Piece {
    ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board);

    char toChar();

    boolean isVoidPiece();

    COLOR getColor();
}
