package com.example.chess;

import java.util.ArrayList;

public interface Piece {
    ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board);

    char toChar();

    boolean isVoidPiece();

    COLOR getColor();

    static boolean cellIsNotVoid(int row, int col, Board board, ArrayList<Cell> results, COLOR color) {
        Piece piece = board.at(row, col).getPiece();
        if (!piece.isVoidPiece()) {
            if (piece.getColor() != color)
                results.add(new Cell(row, col));
            return true;
        }
        results.add(new Cell(row, col));
        return false;
    }
}
