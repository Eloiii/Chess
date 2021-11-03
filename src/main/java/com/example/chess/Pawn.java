package com.example.chess;

import java.util.ArrayList;

public class Pawn implements Piece {

    private final COLOR color;

    public Pawn(COLOR color) {
        this.color = color;
    }


    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();

        int shiftSingle = this.color == COLOR.BLACK ? 1 : -1;
        Piece piece = board.at(rowFrom + shiftSingle, colFrom).getPiece();
        if (piece.isVoidPiece()) {
            results.add(board.at(rowFrom + shiftSingle, colFrom));
            if (rowFrom == 1 || rowFrom == 6) {
                int shiftDouble = this.color == COLOR.BLACK ? 2 : -2;
                piece = board.at(rowFrom + shiftDouble, colFrom).getPiece();
                if (piece.isVoidPiece())
                    results.add(board.at(rowFrom + shiftDouble, colFrom));
            }
        }


        if (colFrom != 7) {
            Piece pieceDiagonalRight = board.at(rowFrom + shiftSingle, colFrom + 1).getPiece();
            if (!pieceDiagonalRight.isVoidPiece() && pieceDiagonalRight.getColor() != this.color)
                results.add(board.at(rowFrom + shiftSingle, colFrom + 1));
        }
        if (colFrom != 0) {
            Piece pieceDiagonalLeft = board.at(rowFrom + shiftSingle, colFrom - 1).getPiece();
            if (!pieceDiagonalLeft.isVoidPiece() && pieceDiagonalLeft.getColor() != this.color)
                results.add(board.at(rowFrom + shiftSingle, colFrom - 1));
        }

        return results;
    }

    @Override
    public char toChar() {
        return 'P';
    }

    @Override
    public boolean isVoidPiece() {
        return false;
    }

    @Override
    public COLOR getColor() {
        return this.color;
    }
}
