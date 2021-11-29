package com.example.chess;

import java.util.ArrayList;

/**
 * Represent a piece on the board
 */
public interface Piece {
    /**
     * Add the move in the array if it is legal
     *
     * @param row     the row of the piece
     * @param col     the col of the piece
     * @param board   the board
     * @param results the legal move list
     * @param color   color of the piece
     * @return true if the move is legal, false if the move is illegal
     */
    static boolean addIfLegalDestination(int row, int col, Board board, ArrayList<Cell> results, COLOR color) {
        Piece piece = board.at(row, col).getPiece();
        if (!piece.isVoidPiece()) {
            if (piece.getColor() != color)
                results.add(board.at(row, col));
            return true;
        }
        results.add(board.at(row, col));
        return false;
    }

    /**
     * Get the legal moves for a piece
     *
     * @param rowFrom the row of the piece
     * @param colFrom the col of the piece
     * @param board   the board
     * @return list of legal movess
     */
    ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board);

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
}
