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
    private ArrayList<Cell> protectedCells;

    public Bishop(COLOR color) {
        this.color = color;
    }

    /**
     * Get legals moves for the bishop :
     * The bishop moves diagonally for an unlimited distance until it reaches another piece or the end of the board
     *
     * @param rowFrom the row of the piece
     * @param colFrom the col of the piece
     * @return legal moves of the bishop
     */
    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom) {
        Board board = Board.getInstance();
        ArrayList<Cell> legalMoves = new ArrayList<>();
        this.protectedCells = new ArrayList<>();

        getBottomRightDiagonalMoves(rowFrom, colFrom, legalMoves);
        getTopLeftDiagonalMoves(rowFrom, colFrom, board, legalMoves);
        getBottomLeftDiagonalMoves(rowFrom, colFrom, board, legalMoves);
        getTopRightDiagonalMoves(rowFrom, colFrom, board, legalMoves);

//        if(check)  { // if my king is check
////            ArrayList<Cell> cellsPreventingCheck = getCellsPreventingCheck(kingPosition, cellPerformingCheck);
//            for (Cell move :
//                    legalMoves) {
//                if (cellsPreventingCheck.contains(move) || move == cellPerformingCheck)
//                    res.add(move);
//            }
//        }

        return legalMoves;
    }

    private void getTopRightDiagonalMoves(int rowFrom, int colFrom, Board board, ArrayList<Cell> results) {
        int rowStopCell;
        int colStopCell;
        int stop;
        Cell stopCell;
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
            if (Piece.addMoveAndTestEmptyCell(rowFrom - (index + 1), colFrom + index + 1, results, protectedCells, this.color)) break;
        }
    }

    private void getBottomLeftDiagonalMoves(int rowFrom, int colFrom, Board board, ArrayList<Cell> results) {
        Cell stopCell;
        int colStopCell;
        int rowStopCell;
        int stop;
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
            if (Piece.addMoveAndTestEmptyCell(rowFrom + index + 1, colFrom - index - 1, results, protectedCells, this.color)) break;
        }
    }

    private void getTopLeftDiagonalMoves(int rowFrom, int colFrom, Board board, ArrayList<Cell> results) {
        int stop;
        int colStopCell;
        int rowStopCell;
        Cell stopCell;
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
            if (Piece.addMoveAndTestEmptyCell(rowFrom - (index + 1), colFrom - index - 1, results, protectedCells, this.color)) break;
        }
    }

    private void getBottomRightDiagonalMoves(int rowFrom, int colFrom, ArrayList<Cell> results) {
        int stop;
        //getting bottom right legal moves
        stop = Math.min(BoardDimensions.MAX_ROW.getValue() - rowFrom, BoardDimensions.MAX_COL.getValue() - colFrom);
        if (colFrom != BoardDimensions.MAX_COL.getValue() - 1) {
            for (int index = rowFrom + 1; index < rowFrom + stop; index++) {
                if (Piece.addMoveAndTestEmptyCell(index, colFrom + (index - rowFrom), results, protectedCells, this.color)) break;
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
