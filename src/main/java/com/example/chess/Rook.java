package com.example.chess;

import java.util.ArrayList;

public class Rook implements Piece {

    private final COLOR color;

    public Rook(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();

        for (int rowFor = rowFrom; rowFor < BoardDimensions.MAX_ROW.getValue(); rowFor++) {
            Piece piece = board.at(rowFor, colFrom).getPiece();
            if (!piece.isVoidPiece() && piece.getColor() == this.color)
                break;
            results.add(new Cell(rowFor, colFrom));
        }
        for (int rowFor = 0; rowFor < rowFrom; rowFor++) {
            Piece piece = board.at(rowFor, colFrom).getPiece();
            if (!piece.isVoidPiece() && piece.getColor() == this.color)
                break;
            results.add(new Cell(rowFor, colFrom));
        }

        for (int colFor = colFrom; colFor < BoardDimensions.MAX_COL.getValue(); colFor++) {
            Piece piece = board.at(rowFrom, colFor).getPiece();
            if (!piece.isVoidPiece() && piece.getColor() == this.color)
                break;
            results.add(new Cell(rowFrom, colFor));
        }
        for (int colFor = 0; colFor < colFrom; colFor++) {
            Piece piece = board.at(rowFrom, colFor).getPiece();
            if (!piece.isVoidPiece() && piece.getColor() == this.color)
                break;
            results.add(new Cell(rowFrom, colFor));
        }
        return results;
    }

    @Override
    public char toChar() {
        return 'R';
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
