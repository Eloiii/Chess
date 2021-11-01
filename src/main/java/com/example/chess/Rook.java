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

        for (int rowFor = rowFrom + 1; rowFor < BoardDimensions.MAX_ROW.getValue(); rowFor++) {
            Piece piece = board.at(rowFor, colFrom).getPiece();
            if (!piece.isVoidPiece()) {
                if (piece.getColor() != this.color)
                    results.add(new Cell(rowFor, colFrom));
                break;
            }
            results.add(new Cell(rowFor, colFrom));
        }
        for (int rowFor = rowFrom - 1; rowFor > -1; rowFor--) {
            Piece piece = board.at(rowFor, colFrom).getPiece();
            if (!piece.isVoidPiece()) {
                if (piece.getColor() != this.color)
                    results.add(new Cell(rowFor, colFrom));
                break;
            }
            results.add(new Cell(rowFor, colFrom));
        }
        for (int colFor = colFrom + 1; colFor < BoardDimensions.MAX_COL.getValue(); colFor++) {
            Piece piece = board.at(rowFrom, colFor).getPiece();
            if (!piece.isVoidPiece()) {
                if (piece.getColor() != this.color)
                    results.add(new Cell(rowFrom, colFor));
                break;
            }
            results.add(new Cell(rowFrom, colFor));
        }
        for (int colFor = rowFrom - 1; colFor > -1; colFor--) {
            Piece piece = board.at(rowFrom, colFor).getPiece();
            if (!piece.isVoidPiece()) {
                if (piece.getColor() != this.color)
                    results.add(new Cell(rowFrom, colFor));
                break;
            }
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
