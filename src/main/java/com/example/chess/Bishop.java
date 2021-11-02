package com.example.chess;

import java.util.ArrayList;

public class Bishop implements Piece {

    private final COLOR color;

    public Bishop(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();
        int stop, rowStopCell, colStopCell;
        Cell stopCell;

        //bas droite
        stop = Math.min(BoardDimensions.MAX_ROW.getValue() - rowFrom, BoardDimensions.MAX_COL.getValue() - colFrom);
        if (colFrom != BoardDimensions.MAX_COL.getValue() - 1) {
            for (int index = rowFrom + 1; index < rowFrom + stop; index++) {
                if (Piece.cellIsNotVoid(index, colFrom + (index - rowFrom), board, results, this.color)) break;
            }
        }


        //haut gauche
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
            if (Piece.cellIsNotVoid(rowFrom - (index + 1), colFrom - index - 1, board, results, this.color)) break;
        }


        //bas gauche
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
            if (Piece.cellIsNotVoid(rowFrom + index + 1, colFrom - index - 1, board, results, this.color)) break;
        }


        //haut droite
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
            if (Piece.cellIsNotVoid(rowFrom - (index + 1), colFrom + index + 1, board, results, this.color)) break;
        }

        return results;
    }

    @Override
    public char toChar() {
        return 'B';
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
