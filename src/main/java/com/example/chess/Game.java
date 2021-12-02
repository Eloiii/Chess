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

//    /**
//     * Get all the possibleMoves for the cell selected
//     *
//     * @return a list of possible moves
//     * @throws CheckException catch illegal piece move when king is check
//     */
//    private ArrayList<Cell> getPossibleMoves() throws CheckException {
//        ArrayList<Cell> possibleMoves;
//        Cell king = board.getCellByPiece(new King(this.turn), this.turn);
//        boolean isKing = this.cellSelected.getPiece() instanceof King;
//        if (((King) king.getPiece()).isCheck())
//            possibleMoves = getSelectedPieceMovesPreventingCheck(king);
//        else {
//            possibleMoves = this.cellSelected.getLegalMovesForPiece();
//            if(isKing)
//                possibleMoves = this.getKingPossibleMoves(possibleMoves);
//            if (!isKing)
//                possibleMoves.removeIf(this::moveCreatesCheck);
//        }
//        return possibleMoves;
//    }
//
//    /**
//     * Get all the moves that can prevent the check for the selected piece
//     *
//     * @param kingPosition the king check
//     * @return list of legal moves that can prevent the check
//     * @throws CheckException catch if the selected piece cannot prevent the check
//     */
//    private ArrayList<Cell> getSelectedPieceMovesPreventingCheck(Cell kingPosition) throws CheckException {
//        ArrayList<Cell> res = new ArrayList<>();
//        ArrayList<Cell> possibleMoves = this.cellSelected.getLegalMovesForPiece();
//        Cell cellPerformingCheck = ((King) kingPosition.getPiece()).cellPerformingCheck;
//        if (this.cellSelected.getPiece() instanceof King) {
//            res = getKingPossibleMoves(possibleMoves);
//        } else {
//            ArrayList<Cell> cellsPreventingCheck = getCellsPreventingCheck(kingPosition, cellPerformingCheck);
//            for (Cell move :
//                    possibleMoves) {
//                if (cellsPreventingCheck.contains(move) || move == cellPerformingCheck) res.add(move);
//            }
//        }
//        if (res.isEmpty()) {
//            try {
//                throw new CheckException("King is check and " + this.cellSelected.toString() + " cannot prevent it");
//            } finally {
//                this.cellSelected = null;
//            }
//        } else
//            return res;
//    }
//
//    private ArrayList<Cell> getKingPossibleMoves(ArrayList<Cell> possibleMoves) {
//        ArrayList<Cell> res = new ArrayList<>();
//        for (Cell move : possibleMoves) {
//            if (kingCanMoveTo(move))
//                res.add(move);
//        }
//        return res;
//    }
//
//    private boolean kingCanMoveTo(Cell move) {
//        ArrayList<Cell> allCellsForOppositColor = board.getAllCellsForColor(this.turn == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK);
//        ArrayList<Cell> everyMoveForAllPieces = new ArrayList<>();
//        ArrayList<Cell> protectedCells = new ArrayList<>();
//        for (Cell cell :
//                allCellsForOppositColor) {
//            ArrayList<Cell> legalMovesForCell = cell.getLegalMovesForPiece();
//            if(cell.getPiece() instanceof Pawn) {
//                for(Cell pawnMove : legalMovesForCell) {
//                    if(pawnMove.getCol() != cell.getCol()) {
//                        everyMoveForAllPieces.add(pawnMove);
//                    }
//                }
//            }
//            else
//                everyMoveForAllPieces.addAll(legalMovesForCell);
//            protectedCells.addAll(cell.getPiece().getProtectedCells());
//        }
//        return (!everyMoveForAllPieces.contains(move) && !protectedCells.contains(move));
//    }
//
//
//
//
//
//    /**
//     * Checks if the move is creating a check on his own king (illegal)
//     *
//     * @param destination the wanted destination of the selected cell
//     * @return true if the move creates an illegal check; false if not
//     */
//    private boolean moveCreatesCheck(Cell destination) {
//        boolean res = false;
//        this.board.setPiece(this.cellSelected.getRow(), this.cellSelected.getCol(), new VoidPiece()); // simule piece pas là
//        ArrayList<Cell> allCellsForOppositColor = board.getAllCellsForColor(this.turn == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK);
//        for (Cell cell :
//                allCellsForOppositColor) {
//            checkAndSetIfKingChecked(cell);
//        }
//        King king = (King) board.getCellByPiece(new King(this.turn), this.turn).getPiece();
//        if (king.isCheck()) {
//            king.setCheck(false, null);
//            res = true;
//        }
//        this.board.setPiece(this.cellSelected.getRow(), this.cellSelected.getCol(), this.cellSelected.getPiece());
//        return res;
//
//
//    }

    /*
     * Checks if the opponent's king has been check
     *
     * @param source the cell where from where the check is checked
     */
    private void checkAndSetIfKingChecked(Cell source) {
        //TODO MAYBE BETTER WAY ?
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
        if (this.cellSelected.getPiece().getColor() != this.turn)
            throw new IllegalMoveException("Wrong color selected : selected " + this.cellSelected.getPiece().getColor() + " but it is " + this.turn + " to move.");
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
