package com.example.chess;

import java.util.ArrayList;

public class Board {
    private final Cell[][] cells;

    public Board() {
        this.cells = new Cell[BoardDimensions.MAX_ROW.getValue()][BoardDimensions.MAX_COL.getValue()];
    }

    public Cell at(int row, int col) throws IndexOutOfBoundsException {
        if (this.outOfBounds(row, col))
            throw new IndexOutOfBoundsException();
        return this.cells[row][col];
    }

    public boolean move(Cell from, int rowTo, int colTo) throws IllegalMoveException {
        if (isALegalMove(from, rowTo, colTo)) {
            this.setPiece(from.getRow(), from.getCol(), new VoidPiece());
            this.setPiece(rowTo, colTo, from.getPiece());
            return true;
        } else
            throw new IllegalMoveException("Illegal move : " + from.getPiece().toChar() + " tried to move from " + from.getRow() + "," + from.getCol() + " to " + rowTo + "," + colTo);
    }

    public void setPiece(int row, int col, Piece piece) throws IndexOutOfBoundsException {
        if (this.outOfBounds(row, col))
            throw new IndexOutOfBoundsException();
        this.cells[row][col] = new Cell(row, col, piece);
    }

    private boolean isALegalMove(Cell cellFrom, int rowTo, int colTo) {
        ArrayList<Cell> possibleMoves = cellFrom.getPiece().getLegalMoves(cellFrom.getRow(), cellFrom.getCol(), this);
        for (Cell currentMove : possibleMoves) {
            if (!this.outOfBounds(currentMove.getRow(), currentMove.getCol())) {
                if (currentMove.getCol() == colTo && currentMove.getRow() == rowTo)
                    return true;
            }
        }
        return false;
    }

    private boolean outOfBounds(int row, int col) {
        return !(row >= 0 && row < BoardDimensions.MAX_ROW.getValue() && col >= 0 && col < BoardDimensions.MAX_COL.getValue());
    }

    public void initPieces() throws IllegalStateException {
        setVoidPieces();
        setPieces(0, COLOR.BLACK);
        setPieces(7, COLOR.WHITE);
    }

    private void setVoidPieces() {
        for (int row = 0; row < BoardDimensions.MAX_ROW.getValue(); row++) {
            for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
                this.setPiece(row, col, new VoidPiece());
            }
        }

    }

    private void setPieces(int row, COLOR color) throws IllegalStateException {
        Piece piece;
        for (int i = 0; i < BoardDimensions.MAX_COL.getValue(); i++) {
            switch (i) {
                case 0:
                case 7:
                    piece = new Rook(color);
                    break;
                case 1:
                case 6:
                    piece = new Knight(color);
                    break;
                case 2:
                case 5:
                    piece = new Bishop(color);
                    break;
                case 3:
                    piece = new Queen(color);
                    break;
                case 4:
                    piece = new King(color);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            this.setPiece(row, i, piece);
        }
        int pawnRow = color == COLOR.BLACK ? 1 : 6;
        for (int j = 0; j < BoardDimensions.MAX_COL.getValue(); j++) {
            this.setPiece(pawnRow, j, new Pawn(color));
        }
    }

    public void printBoard() {
        for (int row = 0; row < BoardDimensions.MAX_ROW.getValue(); row++) {
            for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
                System.out.print(this.cells[row][col].getPiece().toChar());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
