package com.example.chess;

import java.util.ArrayList;

public class Game {
    private final Board board;
    private int turnNumber;
    private COLOR turn;
    private Cell pieceSelected;

    private Game() {
        this.board = Board.getInstance();
        this.turn = COLOR.WHITE;
        this.pieceSelected = null;
    }

    private static final Game INSTANCE = new Game();

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

    public void selectPiece(int row, int col, WindowController controller) throws IllegalMoveException {
        controller.resetColors(this.board);
        if (this.pieceSelected == null || this.pieceSelected.getPiece().getColor() == this.board.at(row, col).getPiece().getColor()) {
            this.pieceSelected = this.board.at(row, col);
            ArrayList<Cell> possibleMoves = this.pieceSelected.getPiece().getLegalMoves(row, col, this.board);
            for (Cell move :
                    possibleMoves) {
                controller.colorCell(move, "red");
            }
            if (this.pieceSelected.getPiece().getColor() != this.turn)
                throw new IllegalMoveException("Wrong color selected : selected " + this.pieceSelected.getPiece().getColor() + " but it is " + this.turn + " to move.");
        } else
            makeAMove(row, col, controller);
    }

    private void makeAMove(int row, int col, WindowController controller) throws IllegalMoveException {
        this.board.move(this.pieceSelected, row, col);
        controller.moveImages(this.pieceSelected, row, col);
        this.pieceSelected = null;
        this.nextTurn();
        this.changeTurn();
    }

    public boolean gameOver() {
        return false;
    }
}
