package com.example.chess;


import java.util.ArrayList;

/**
 * Representing a game of chess
 * The Game class is a Singleton
 */
public class Game {
    /**
     * The one and only instance of this class (one game is played)
     */
    private static final Game INSTANCE = new Game();
    /**
     * The board on which the game is played
     */
    private final Board board;
    /**
     * The current turn number
     */
    private int turnNumber;
    /**
     * Represents who is playing the next move (BLACK or WHITE)
     */
    private COLOR turn;
    /**
     * The cell selected by the player
     */
    private Cell cellSelected;

    /**
     * Creates a new game
     */
    private Game() {
        this.board = Board.getInstance();
        this.turn = COLOR.WHITE;
        this.cellSelected = null;
    }

    /**
     * Get the instance of the game
     *
     * @return the instance of the game
     */
    public static Game getInstance() {
        return INSTANCE;
    }

    /**
     * Change the turn
     * BLACK -> WHITE
     * or
     * WHITE -> BLACK
     */
    private void changeTurn() {
        if (this.turn == COLOR.BLACK)
            this.turn = COLOR.WHITE;
        else
            this.turn = COLOR.BLACK;
    }

    /**
     * Add one to the turn number
     */
    public void nextTurn() {
        this.turnNumber++;
    }

    /**
     * Get the board of the game
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the selected cell
     * If the cell selected is not null and the color is the opponent, a move is played
     *
     * @param row        the row of the cell
     * @param col        the column of the cell
     * @param controller the JavaFX controller class
     * @throws IllegalMoveException catch illegal move
     * @throws CheckException       catch illegal move when there is a check
     */
    public void selectCell(int row, int col, WindowController controller) throws IllegalMoveException, CheckException {
        controller.resetColors(this.board);
        if (this.cellSelected == null || this.cellSelected.getPiece().getColor() == this.board.at(row, col).getPiece().getColor()) {
            this.cellSelected = this.board.at(row, col);
            checkWrongColorSelected();
            ArrayList<Cell> possibleMoves = this.cellSelected.getLegalMovesForPiece();
            colorPossibleMoves(possibleMoves, controller);
        } else {
            makeAMove(row, col, controller);
        }
    }

    /*
     * Checks if the opponent's king has been check
     *
     * @param source the cell where from where the check is checked
     */
    private void checkAndSetIfKingChecked(Cell source) {
        ArrayList<Cell> possibleMoves = source.getLegalMovesForPiece();
        for (Cell move :
                possibleMoves) {
            Cell cell = this.board.at(move.getRow(), move.getCol());
            if (cell.getPiece() instanceof King) {
                ((King) cell.getPiece()).setCheck(true, source);
                return;
            }
        }
        COLOR color = this.turn == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK;
        ((King) board.getCellByPiece(new King(color), color).getPiece()).setCheck(false, null);
    }

    /**
     * Colors all the possible moves
     *
     * @param moves      the legal moves
     * @param controller the JavaFX window controller
     */
    private void colorPossibleMoves(ArrayList<Cell> moves, WindowController controller) {
        for (Cell move : moves)
            controller.colorCell(move, "red");
    }

    /**
     * Cehck is the player has selected the wrong piece color
     *
     * @throws IllegalMoveException thrown if the wrong color is selected
     */
    private void checkWrongColorSelected() throws IllegalMoveException {
        if (this.cellSelected.getPiece().getColor() != this.turn) {
            try {
                throw new IllegalMoveException("Wrong color selected : selected " + this.cellSelected.getPiece().getColor() + " but it is " + this.turn + " to move.");
            } finally {
                this.cellSelected = null;
            }
        }
    }

    /**
     * Moving a piece on the board
     *
     * @param row        destination row
     * @param col        destination column
     * @param controller the JavaFX window controller
     * @throws IllegalMoveException thrown when illegal move
     */
    private void makeAMove(int row, int col, WindowController controller) throws IllegalMoveException {
        this.board.move(this.cellSelected, row, col);
        controller.moveImages(this.cellSelected, row, col);
        Cell destination = this.board.at(row, col);
        checkAndSetIfKingChecked(destination);
        this.cellSelected = null;
        this.nextTurn();
        this.changeTurn();
    }

    /**
     * Check if checkmate
     *
     * @return true if checkmate, false if game is still running
     */
    public boolean gameOver() {
        return false;
    }
}
