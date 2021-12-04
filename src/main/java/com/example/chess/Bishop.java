package com.example.chess;

import java.util.ArrayList;

/**
 * Represents a Bishop
 */
public class Bishop implements Piece {

    /**
     * The color of the bishop
     */
    private final COLOR color;

    /**
     * Cells (pieces of the same color) that this piece is protecting)
     */
    private final ArrayList<Cell> protectedCells;

    public Bishop(COLOR color) {
        this.color = color;
        this.protectedCells = new ArrayList<>();
    }

    public boolean isPinned() {
        return false;
    }

    public ArrayList<Cell> getBasicMoves(Cell position) {
        Board board = Board.getInstance();
        ArrayList<Cell> results = new ArrayList<>();
        getBottomRightDiagonalMoves(position, results);
        getTopLeftDiagonalMoves(position, board, results);
        getBottomLeftDiagonalMoves(position, board, results);
        getTopRightDiagonalMoves(position, board, results);
        return results;
    }

    private void getTopRightDiagonalMoves(Cell source, Board board, ArrayList<Cell> results) {
        int rowStopCell;
        int colStopCell;
        int stop;
        Cell stopCell;
        int rowFrom = source.getRow();
        int colFrom = source.getCol();
        //getting top right legal moves
        if (rowFrom + colFrom < BoardDimensions.MAX_ROW.getValue() - 1) {
            rowStopCell = 0;
            colStopCell = rowFrom + colFrom;
        } else {
            rowStopCell = (rowFrom + colFrom) - (BoardDimensions.MAX_COL.getValue() - 1);
            colStopCell = BoardDimensions.MAX_COL.getValue() - 1;
        }
        stopCell = board.at(rowStopCell, colStopCell);
        stop = board.at(rowFrom, colFrom).distanceFrom(stopCell);
        for (int index = 0; index < stop; index++) {
            if (Piece.addMoveAndTestEmptyCell(rowFrom - (index + 1), colFrom + index + 1, results, protectedCells, this.color))
                break;
        }
    }

    private void getBottomLeftDiagonalMoves(Cell source, Board board, ArrayList<Cell> results) {
        Cell stopCell;
        int colStopCell;
        int rowStopCell;
        int stop;
        int rowFrom = source.getRow();
        int colFrom = source.getCol();
        //getting bottom left legal moves
        if (rowFrom + colFrom < BoardDimensions.MAX_ROW.getValue() - 1) {
            rowStopCell = rowFrom + colFrom;
            colStopCell = 0;
        } else {
            rowStopCell = BoardDimensions.MAX_COL.getValue() - 1;
            colStopCell = (rowFrom + colFrom) - (BoardDimensions.MAX_COL.getValue() - 1);
        }
        stopCell = board.at(rowStopCell, colStopCell);
        stop = board.at(rowFrom, colFrom).distanceFrom(stopCell);
        for (int index = 0; index < stop; index++) {
            if (Piece.addMoveAndTestEmptyCell(rowFrom + index + 1, colFrom - index - 1, results, protectedCells, this.color))
                break;
        }
    }

    private void getTopLeftDiagonalMoves(Cell source, Board board, ArrayList<Cell> results) {
        int stop;
        int colStopCell;
        int rowStopCell;
        Cell stopCell;
        int rowFrom = source.getRow();
        int colFrom = source.getCol();
        //getting top left legal moves
        if (rowFrom > colFrom) {
            rowStopCell = rowFrom - colFrom;
            colStopCell = 0;
        } else {
            rowStopCell = 0;
            colStopCell = colFrom - rowFrom;
        }
        stopCell = board.at(rowStopCell, colStopCell);
        stop = board.at(rowFrom, colFrom).distanceFrom(stopCell);
        for (int index = 0; index < stop; index++) {
            if (Piece.addMoveAndTestEmptyCell(rowFrom - (index + 1), colFrom - index - 1, results, protectedCells, this.color))
                break;
        }
    }

    private void getBottomRightDiagonalMoves(Cell source, ArrayList<Cell> results) {
        int stop;
        int rowFrom = source.getRow();
        int colFrom = source.getCol();
        //getting bottom right legal moves
        stop = Math.min(BoardDimensions.MAX_ROW.getValue() - rowFrom, BoardDimensions.MAX_COL.getValue() - colFrom);
        if (colFrom != BoardDimensions.MAX_COL.getValue() - 1) {
            for (int index = rowFrom + 1; index < rowFrom + stop; index++) {
                if (Piece.addMoveAndTestEmptyCell(index, colFrom + (index - rowFrom), results, protectedCells, this.color))
                    break;
            }
        }
    }

    /**
     * Returns 'B'
     *
     * @return 'B'
     */
    @Override
    public char toChar() {
        return 'B';
    }

    /**
     * Returns false
     *
     * @return false
     */
    @Override
    public boolean isVoidPiece() {
        return false;
    }

    /**
     * Get the color of the bishop
     *
     * @return the color of the bishop
     */
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
