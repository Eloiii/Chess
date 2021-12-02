package com.example.chess;

import java.util.ArrayList;

public class Pawn implements Piece {

    private final COLOR color;
    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private ArrayList<Cell> protectedCells;

    public Pawn(COLOR color) {
        this.color = color;
    }


    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom) {
        Board board = Board.getInstance();
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();

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
            Cell cell = board.at(rowFrom + shiftSingle, colFrom + 1);
            Piece pieceDiagonalRight = cell.getPiece();
            if (!pieceDiagonalRight.isVoidPiece()) {
                if(pieceDiagonalRight.getColor() != this.color)
                    results.add(cell);
                else
                    this.protectedCells.add(cell);
            }
        }
        if (colFrom != 0) {
            Cell cell = board.at(rowFrom + shiftSingle, colFrom - 1);
            Piece pieceDiagonalLeft = cell.getPiece();
            if (!pieceDiagonalLeft.isVoidPiece()) {
                if(pieceDiagonalLeft.getColor() != this.color)
                    results.add(cell);
                else
                    this.protectedCells.add(cell);
            }
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

    /**
     * Get protected cells
     */
    @Override
    public ArrayList<Cell> getProtectedCells() {
        return this.protectedCells;
    }
}
