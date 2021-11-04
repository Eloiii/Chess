package com.example.chess;

import java.util.ArrayList;

public interface Piece {
    static boolean addIfLegalCell(int row, int col, Board board, ArrayList<Cell> results, COLOR color) {
        Piece piece = board.at(row, col).getPiece();
        if (!piece.isVoidPiece()) {
            if (piece.getColor() != color)
                results.add(board.at(row, col));
            return true;
        }
        results.add(board.at(row, col));
        return false;
    }

    ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board);

    char toChar();

    boolean isVoidPiece();

    COLOR getColor();
}
