package com.example.chess;

import java.util.ArrayList;

public interface Piece {
    ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board);

    char toChar();

    boolean isVoidPiece();

    COLOR getColor();

    static boolean checkCell(int rowFor, int colFrom, Board board, ArrayList<Cell> results, COLOR color) {
        Piece piece = board.at(rowFor, colFrom).getPiece();
        if (!piece.isVoidPiece()) {
            if (piece.getColor() != color)
                results.add(new Cell(rowFor, colFrom));
            return true;
        }
        results.add(new Cell(rowFor, colFrom));
        return false;
    }
}
