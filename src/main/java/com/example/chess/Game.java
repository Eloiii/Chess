package com.example.chess;


import java.util.ArrayList;

public class Game {
    private static final Game INSTANCE = new Game();
    private final Board board;
    private int turnNumber;
    private COLOR turn;
    private Cell cellSelected;

    private Game() {
        this.board = Board.getInstance();
        this.turn = COLOR.WHITE;
        this.cellSelected = null;
    }

    public static Game getInstance() {
        return INSTANCE;
    }

    private void changeTurn() {
        if (this.turn == COLOR.BLACK)
            this.turn = COLOR.WHITE;
        else
            this.turn = COLOR.BLACK;
    }

    public void nextTurn() {
        this.turnNumber++;
    }

    public Board getBoard() {
        return board;
    }

    public void selectPiece(int row, int col, WindowController controller) throws IllegalMoveException, CheckException {
        controller.resetColors(this.board);

        if (this.cellSelected == null || this.cellSelected.getPiece().getColor() == this.board.at(row, col).getPiece().getColor()) {
            this.cellSelected = this.board.at(row, col);
            colorPossibleMoves(row, col, controller);
            checkWrongColorSelected();
            checkPieceMovedWhenCheck();
        } else {
            makeAMove(row, col, controller);
        }
    }

    private void checkPieceMovedWhenCheck() throws CheckException {
        if (((King) board.getPiece(new King(this.turn), this.turn).getPiece()).isCheck() && !(this.cellSelected.getPiece() instanceof King))
            throw new CheckException("King is check");
    }

    private void checkWrongColorSelected() throws IllegalMoveException {
        if (this.cellSelected.getPiece().getColor() != this.turn)
            throw new IllegalMoveException("Wrong color selected : selected " + this.cellSelected.getPiece().getColor() + " but it is " + this.turn + " to move.");
    }

    private void colorPossibleMoves(int row, int col, WindowController controller) {
        ArrayList<Cell> possibleMoves;
        possibleMoves = this.cellSelected.getPiece().getLegalMoves(row, col, this.board);
        for (Cell move :
                possibleMoves) {
            controller.colorCell(move, "red");
        }
    }

    private void checkKingCheck(ArrayList<Cell> possibleMoves) {
        for (Cell move :
                possibleMoves) {
            Piece piece = this.board.at(move.getRow(), move.getCol()).getPiece();
            if (piece instanceof King) {
                ((King) piece).setCheck(true);
                break;
            }
        }
    }

    private void makeAMove(int row, int col, WindowController controller) throws IllegalMoveException {
        this.board.move(this.cellSelected, row, col);
        controller.moveImages(this.cellSelected, row, col);
        Cell destination = this.board.at(row, col);
        checkKingCheck(destination.getPiece().getLegalMoves(row, col, this.board));
        this.cellSelected = null;
        this.nextTurn();
        this.changeTurn();
    }

    public boolean gameOver() {
        return false;
    }
}
