package com.example.chess;

import java.util.ArrayList;

/**
 * Represent a piece on the board
 */
public interface Piece {

    /**
     * Add the move in the array of possible moves
     *
     * @param row     the row of the piece
     * @param col     the col of the piece
     * @param possibleMoves the legal move list
     * @param color   color of the piece
     * @return true if the destination cell is an empty cell, false if the destination cell is an opponent piece
     */
    static boolean addMoveAndTestEmptyCell(int row, int col, ArrayList<Cell> possibleMoves, ArrayList<Cell> protectedCells, COLOR color) {
        Board board = Board.getInstance();
        Cell cell = board.at(row, col);
        Piece piece = cell.getPiece();
        if (!piece.isVoidPiece()) {
            if(piece.getColor() != color)
                possibleMoves.add(cell);
            else
                protectedCells.add(cell);
            return true;
        }
        possibleMoves.add(cell);
        return false;
    }

    /**
     * Get the legal moves for a piece
     *
     * @param rowFrom the row of the piece
     * @param colFrom the col of the pieceboard
     * @return list of legal movess
     */
    ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom);

    /**
     * Get char representing the piece
     *
     * @return 'P' -> Pawn / 'Q' -> Queen / 'K' -> King / 'R' -> Rook / 'B' -> Bishop / 'N' -> Knight
     */
    char toChar();

    /**
     * Check if the piece is an actual piece or an empty one (on an empty cell of the board)
     *
     * @return true if it is a void piece, false if it is a real piece
     */
    boolean isVoidPiece();

    /**
     * Get the color of the piece
     *
     * @return the color of the piece
     */
    COLOR getColor();

    /**
     * Get protected cells
     */
    ArrayList<Cell> getProtectedCells();
}
