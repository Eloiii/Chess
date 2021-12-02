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
    public ArrayList<Cell> getLegalMoves(int row, int col) {
        this.protectedCells = new ArrayList<>();
        boolean checkOnMyKing = Piece.checkOnKing(this.color);
        Board board = Board.getInstance();
        ArrayList<Cell> possibleMoves = getBasicMoves(row, col);
        Piece.filterMoves(checkOnMyKing, board, possibleMoves, isPinned(), this.color);
        return possibleMoves;
    }


    public ArrayList<Cell> getBasicMoves(int row, int col) {
        Board board = Board.getInstance();
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();

        int shiftSingle = this.color == COLOR.BLACK ? 1 : -1;
        Piece piece = board.at(row + shiftSingle, col).getPiece();
        if (piece.isVoidPiece()) {
            results.add(board.at(row + shiftSingle, col));
            if (row == 1 || row == 6) {
                int shiftDouble = this.color == COLOR.BLACK ? 2 : -2;
                piece = board.at(row + shiftDouble, col).getPiece();
                if (piece.isVoidPiece())
                    results.add(board.at(row + shiftDouble, col));
            }
        }


        if (col != 7) {
            Cell cell = board.at(row + shiftSingle, col + 1);
            Piece pieceDiagonalRight = cell.getPiece();
            if (!pieceDiagonalRight.isVoidPiece()) {
                if (pieceDiagonalRight.getColor() != this.color)
                    results.add(cell);
                else
                    this.protectedCells.add(cell);
            }
        }
        if (col != 0) {
            Cell cell = board.at(row + shiftSingle, col - 1);
            Piece pieceDiagonalLeft = cell.getPiece();
            if (!pieceDiagonalLeft.isVoidPiece()) {
                if (pieceDiagonalLeft.getColor() != this.color)
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

    @Override
    public boolean isPinned() {
        return false;
    }
}
