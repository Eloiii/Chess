package com.example.chess;

import java.util.ArrayList;

public class King implements Piece {

    private final COLOR color;
    public Cell cellPerformingCheck;
    private boolean isInCheck;

    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private ArrayList<Cell> protectedCells;

    public King(COLOR color) {
        this.color = color;
        this.protectedCells = new ArrayList<>();
        this.isInCheck = false;
    }

    public ArrayList<Cell> getBasicMoves(Cell position) {
        ArrayList<Cell> results = new ArrayList<>();
        this.protectedCells = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();
        if (row != BoardDimensions.MAX_ROW.getValue() - 1) {
            Piece.addMoveAndTestEmptyCell(row + 1, col, results, protectedCells, this.color);
            if (col != BoardDimensions.MAX_COL.getValue() - 1)
                Piece.addMoveAndTestEmptyCell(row + 1, col + 1, results, protectedCells, this.color);
            if (col != 0)
                Piece.addMoveAndTestEmptyCell(row + 1, col - 1, results, protectedCells, this.color);
        }
        if (row != 0) {
            Piece.addMoveAndTestEmptyCell(row - 1, col, results, protectedCells, this.color);
            if (col != BoardDimensions.MAX_COL.getValue() - 1)
                Piece.addMoveAndTestEmptyCell(row - 1, col + 1, results, protectedCells, this.color);
            if (col != 0)
                Piece.addMoveAndTestEmptyCell(row - 1, col - 1, results, protectedCells, this.color);
        }
        if (col != BoardDimensions.MAX_COL.getValue() - 1)
            Piece.addMoveAndTestEmptyCell(row, col + 1, results, protectedCells, this.color);
        if (col != 0)
            Piece.addMoveAndTestEmptyCell(row, col - 1, results, protectedCells, this.color);
        results.addAll(getCastlingMoves());
        return results;
    }

    private ArrayList<Cell> getCastlingMoves() {
        ArrayList<Cell> res = new ArrayList<>();
        Board board = Board.getInstance();
        //king side
        //TODO GET MOVES (from branch graphicRevamp) AND CHANGE IT TO HAVE CELLS INSTEAD OF STRINGS, CHECK THE PIECE OF THE CELLS IF THE KING APPEARS
        //TODO IF IT APPEARS : NOT LEGAL TO CASTLE
        //TODO ELSE CHECK IF THE ROOK DIDNT MOVE AND NO PIECES BETWEEN KING AND ROOK AND NO CELL CONTROLLED BY OPPONENT PIECE ON THE KINGS WAY
        return res;
    }

    @Override
    public char toChar() {
        return 'K';
    }

    @Override
    public boolean isVoidPiece() {
        return false;
    }

    @Override
    public COLOR getColor() {
        return this.color;
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public void setCheck(boolean check, Cell piece) {
        this.isInCheck = check;
        if (!check)
            this.cellPerformingCheck = null;
        else
            this.cellPerformingCheck = piece;
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
