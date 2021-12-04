package com.example.chess;


import java.util.ArrayList;

/**
 * Represent a piece on the board
 */
public interface Piece {

    /**
     * Add the move in the array of possible moves
     *
     * @param row           the row of the piece
     * @param col           the col of the piece
     * @param possibleMoves the legal move list
     * @param color         color of the piece
     * @return true if the destination cell is an empty cell, false if the destination cell is an opponent piece
     */
    static boolean addMoveAndTestEmptyCell(int row, int col, ArrayList<Cell> possibleMoves, ArrayList<Cell> protectedCells, COLOR color) {
        Board board = Board.getInstance();
        Cell cell = board.at(row, col);
        Piece piece = cell.getPiece();
        if (!piece.isVoidPiece()) {
            if (piece.getColor() != color)
                possibleMoves.add(cell);
            else
                protectedCells.add(cell);
            return true;
        }
        possibleMoves.add(cell);
        return false;
    }

    static void keepOnlyCheckCounters(ArrayList<Cell> basicMoves, Cell myKingCell) {
        King myKing = (King) myKingCell.getPiece();
        basicMoves.removeIf(dest -> !isBetweenKingAndCheckingPiece(dest, myKingCell) && !isEatingCheckingPiece(dest, myKing));
    }

    static boolean isEatingCheckingPiece(Cell dest, King myKingCell) {
        return dest == myKingCell.cellPerformingCheck;
    }

    static boolean isBetweenKingAndCheckingPiece(Cell dest, Cell myKingCell) {
        Board board = Board.getInstance();
        Cell cellPerformingCheck = ((King) myKingCell.getPiece()).cellPerformingCheck;
        return board.getCellsBetween(myKingCell, cellPerformingCheck).contains(dest);
    }

    static boolean checkOnKing(COLOR color) {
        Board board = Board.getInstance();
        King myKing = (King) board.getCellByPiece(new King(color), color).getPiece();
        return myKing.isCheck();
    }

    static void filterMoves(boolean checkOnMyKing, Board board, ArrayList<Cell> basicMoves, boolean isPinned, COLOR color) {
        if (checkOnMyKing) {
            Cell myKingCell = board.getCellByPiece(new King(color), color);
            Piece.keepOnlyCheckCounters(basicMoves, myKingCell);
        } else {
            if (isPinned)
                filterPinned(basicMoves);
        }
    }


    static void filterPinned(ArrayList<Cell> basicMoves) {

    }

    /**
     * Get the legal moves for a piece
     *
     * @param source         @return list of legal movess
     * @param specifiedPiece used by the queen to get bishop and rook moves
     */
    static ArrayList<Cell> getLegalMoves(Cell source, Piece specifiedPiece) {
        Board board = Board.getInstance();
        Piece targetedPiece;
        targetedPiece = specifiedPiece == null ? board.at(source.getRow(), source.getCol()).getPiece() : specifiedPiece;
        boolean checkOnMyKing = Piece.checkOnKing(targetedPiece.getColor());
        ArrayList<Cell> possibleMoves = targetedPiece.getBasicMoves(source);
        if (targetedPiece instanceof King) {
            if (checkOnMyKing)
                possibleMoves.removeIf(move -> board.isUnderAttack(move, targetedPiece.getColor() == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK));
            //TODO check if cell is protected
        } else
            Piece.filterMoves(checkOnMyKing, board, possibleMoves, targetedPiece.isPinned(), targetedPiece.getColor());

        return possibleMoves;
    }

    /**
     * Get all the moves the piece can do whatever the state of the board
     *
     * @param position the position of the piece
     * @return all the intial moves the piece can do
     */
    ArrayList<Cell> getBasicMoves(Cell position);

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

    boolean isPinned();
}
