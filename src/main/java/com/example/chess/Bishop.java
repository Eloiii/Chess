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

    public Bishop(COLOR color) {
        this.color = color;
    }

    /**
     * Get legals moves for the bishop :
     * The bishop moves diagonally for an unlimited distance until it reaches another piece or the end of the board
     *
     * @param rowFrom the row of the piece
     * @param colFrom the col of the piece
     * @param board   the board
     * @return legal moves of the bishop
     */
    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();
        int stop, rowStopCell, colStopCell;
        Cell stopCell;

        //getting bottom right legal moves
        stop = Math.min(BoardDimensions.MAX_ROW.getValue() - rowFrom, BoardDimensions.MAX_COL.getValue() - colFrom);
        if (colFrom != BoardDimensions.MAX_COL.getValue() - 1) {
            for (int index = rowFrom + 1; index < rowFrom + stop; index++) {
                if (Piece.addIfLegalCell(index, colFrom + (index - rowFrom), board, results, this.color)) break;
            }
        }


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
            if (Piece.addIfLegalCell(rowFrom - (index + 1), colFrom - index - 1, board, results, this.color)) break;
        }


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
            if (Piece.addIfLegalCell(rowFrom + index + 1, colFrom - index - 1, board, results, this.color)) break;
        }


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
            if (Piece.addIfLegalCell(rowFrom - (index + 1), colFrom + index + 1, board, results, this.color)) break;
        }

        return results;
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
}
