package com.example.chess;

import java.util.ArrayList;

/**
 * Represents a cell of the board
 */
public class Cell {
    /**
     * Row the cell
     */
    private final int row;
    /**
     * Column of the cell
     */
    private final int col;
    /**
     * The piece standing on the cell
     */
    private Piece piece;

    /**
     * Creates a new cell for a given postion
     *
     * @param x the row
     * @param y the column
     */
    public Cell(int x, int y) {
        this.row = x;
        this.col = y;
        this.piece = new VoidPiece();
    }

    /**
     * Creates a new cell for a given position and a piece
     *
     * @param x     the row
     * @param y     the column
     * @param piece the piece
     */
    public Cell(int x, int y, Piece piece) {
        this.row = x;
        this.col = y;
        this.piece = piece;
    }

    /**
     * Get the piece standing on the cell
     *
     * @return the piece
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Set a piece on the cell
     *
     * @param piece the piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Get the row
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column
     *
     * @return the column
     */
    public int getCol() {
        return col;
    }

    /**
     * Calculates the distance from this cell to another
     *
     * @param to the destination cell
     * @return an integer representing the distance between this cell and the 'to' cell
     */
    public int distanceFrom(Cell to) {
        int diffRow = to.getRow() - this.getRow();
        int diffCol = to.getCol() - this.getCol();
        return Math.abs(Math.max(diffCol, diffRow));
    }

    /**
     * Prints the string
     *
     * @return a String describing the cell
     */
    public String toString() {
        return this.piece.toChar() + " at [" + this.row + "," + this.col + "]";
    }

    /**
     * Returns the legal moves of the piece standing on the cell
     *
     * @return the list of legal moves
     */
    public ArrayList<Cell> getLegalMovesForPiece() {
        return this.piece.getLegalMoves(this.row, this.col);
    }

    public boolean hasSameCoordinates(Cell cell) {
        return (cell.getRow() == this.getRow()) && (cell.getCol() == this.getCol());
    }
}
