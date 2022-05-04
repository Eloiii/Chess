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

    public boolean checkMate;

    public ArrayList<Move> moves;

    private Move currentMove;

    /**
     * Creates a new game
     */
    private Game() {
        this.board = Board.getInstance();
        this.turn = COLOR.WHITE;
        this.cellSelected = null;
        this.checkMate = false;
        this.moves = new ArrayList<>();
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
    private void changeTurn(WindowController controller) {
        if(this.turn == COLOR.BLACK) {
            controller.pauseBlackClock();
            controller.resumeWhiteTimer();
            this.turn = COLOR.WHITE;
        } else {
            controller.pauseWhiteClock();
            controller.resumeBlackTimer();
            this.turn = COLOR.BLACK;
        }

    }

    /**
     * Add one to the turn number
     */
    public void nextTurn() {
        if (this.turn == COLOR.BLACK)
            this.turnNumber++;
    }

    public COLOR getOpponent() {
        return this.turn == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK;
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
        if(this.checkMate)
            return;
        controller.resetColors(this.board);
        if (this.cellSelected == null || this.cellSelected.getPiece().getColor() == this.board.at(row, col).getPiece().getColor()) {
            this.cellSelected = this.board.at(row, col);
            checkWrongColorSelected();
            ArrayList<Cell> possibleMoves = this.cellSelected.getLegalMovesForPiece();
            colorPossibleMoves(possibleMoves, controller);
        } else {
            processMove(controller, this.board.at(row, col));
        }
    }

    /*
     * Checks if the opponent's king has been check
     *
     * @param source the cell where from where the check is checked
     */
    private void checkAndSetIfKingChecked(Cell source) {
        if(source.getPiece() instanceof King)
            return;
        ArrayList<Cell> possibleMoves = source.getLegalMovesForPiece();
        for (Cell move :
                possibleMoves) {
            Cell cell = this.board.at(move.getRow(), move.getCol());
            if (cell.getPiece() instanceof King) {
                ((King) cell.getPiece()).setCheck(true, source);
                return;
            }
        }
        COLOR color = this.getOpponent();
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
            controller.colorCell(move, "#811331");
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
     * @param controller the JavaFX window controller
     * @param dest       the destination cell
     */
    private void processMove(WindowController controller, Cell dest) {
        String move;
        try {

            move = this.board.move(this.cellSelected, dest);
            Cell destination = this.board.at(dest.getRow(), dest.getCol());
            this.checkAndSetIfKingChecked(destination);
            controller.moveImages(this.cellSelected, dest);
            this.colorLastMove(controller, dest);
            this.isGameOver();
            this.cellSelected = null;
            this.addMove(controller, move);
            this.nextTurn();
            this.changeTurn(controller);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
    }

    private void colorLastMove(WindowController controller, Cell dest) {
        controller.colorCell(this.cellSelected, "#E1C699");
        controller.colorCell(dest, "#E1C699");
    }

    private void addMove(WindowController controller, String move) {
        if (this.turn == COLOR.BLACK) {
            this.currentMove.setBlackMove(move);
            this.moves.add(this.currentMove);

        } else
            this.currentMove = new Move(move);
        controller.addMove(this.currentMove);
    }

    /**
     * Check if checkmate
     */
    public void isGameOver() {
        //TODO PAT
        COLOR opponent = this.getOpponent();
        Cell opponentKing = this.board.getCellByPiece(new King(opponent), opponent);
        ArrayList<Cell> possibleMoves = opponentKing.getLegalMovesForPiece();
        if(possibleMoves.isEmpty() && ((King) opponentKing.getPiece()).isInCheck()) {
            this.checkMate = true;
            System.out.println(this.turn + " WINS BY CHECKMATE");
        }
    }
}
