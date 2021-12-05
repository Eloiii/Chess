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
        this.protectedCells = new ArrayList<>();
    }


    public ArrayList<Cell> getBasicMoves(Cell position) {
        Board board = Board.getInstance();
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

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


        results.addAll(getDiagonalCells(board, position, false));

        return results;
    }

    public ArrayList<Cell> getDiagonalCells(Board board, Cell position, boolean omitVoidPieces) {
        ArrayList<Cell> res = new ArrayList<>();
        int shiftSingle = this.color == COLOR.BLACK ? 1 : -1;
        if (position.getCol() != 7) {
            Cell cell = board.at(position.getRow() + shiftSingle, position.getCol() + 1);
            Piece pieceDiagonalRight = cell.getPiece();
            if (!pieceDiagonalRight.isVoidPiece() || omitVoidPieces) {
                if (pieceDiagonalRight.getColor() != this.color)
                    res.add(cell);
                else
                    this.protectedCells.add(cell);
            }
        }
        if (position.getCol() != 0) {
            Cell cell = board.at(position.getRow() + shiftSingle, position.getCol() - 1);
            Piece pieceDiagonalLeft = cell.getPiece();
            if (!pieceDiagonalLeft.isVoidPiece() || omitVoidPieces) {
                if (pieceDiagonalLeft.getColor() != this.color)
                    res.add(cell);
                else
                    this.protectedCells.add(cell);
            }
        }
        return res;
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
