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

    public void selectCase(int row, int col, WindowController controller) throws IllegalMoveException, CheckException {
        controller.resetColors(this.board);
        ArrayList<Cell> possibleMoves;
        if (this.cellSelected == null || this.cellSelected.getPiece().getColor() == this.board.at(row, col).getPiece().getColor()) {
            this.cellSelected = this.board.at(row, col);
            checkWrongColorSelected();
            possibleMoves = getPossibleMoves();
            colorPossibleMoves(possibleMoves, controller);
        } else {
            makeAMove(row, col, controller);
        }
    }

    private ArrayList<Cell> getPossibleMoves() throws CheckException {
        ArrayList<Cell> possibleMoves;
        Cell king = board.getCellByPiece(new King(this.turn), this.turn);
        if (((King) king.getPiece()).isCheck())
            possibleMoves = getSelectedPieceMovesPreventingCheck(king);
        else {
            possibleMoves = this.cellSelected.getLegalMovesForPiece(this.board);
            possibleMoves.removeIf(this::doesMoveDoDiscoveryCheck);
        }
        return possibleMoves;
    }

    private ArrayList<Cell> getSelectedPieceMovesPreventingCheck(Cell kingPosition) throws CheckException {
        ArrayList<Cell> res = new ArrayList<>();
        ArrayList<Cell> possibleMoves = this.cellSelected.getLegalMovesForPiece(this.board);
        for (Cell move :
                possibleMoves) {
            if (doesMoveCanPreventCheck(move, kingPosition)) res.add(move);
        }
        if (res.isEmpty()) {
            this.cellSelected = null;
            throw new CheckException("King is check");
        } else
            return res;
    }

    private boolean doesMoveCanPreventCheck(Cell destination, Cell king) {
        Cell cellPerformsCheck = ((King) king.getPiece()).cellPerformsCheck;
        if (destination == cellPerformsCheck)
            return true;
        int diff_X = king.getRow() - cellPerformsCheck.getRow();
        int diff_Y = king.getCol() - cellPerformsCheck.getCol();
        int distance = king.distanceFrom(cellPerformsCheck);
        double interval_X = diff_X / (distance + 1f);
        double interval_Y = diff_Y / (distance + 1f);
        for (int i = 1; i < 3; i++) {
            int x = (int) Math.round(cellPerformsCheck.getRow() + interval_X * i);
            int y = (int) Math.round(cellPerformsCheck.getCol() + interval_Y * i);
            Cell cell = this.board.at(x, y);
            if (destination == cell && !(cellSelected.getPiece() instanceof King)) return true;
        }
        return false;
    }

    private void checkWrongColorSelected() throws IllegalMoveException {
        if (this.cellSelected.getPiece().getColor() != this.turn)
            throw new IllegalMoveException("Wrong color selected : selected " + this.cellSelected.getPiece().getColor() + " but it is " + this.turn + " to move.");
    }

    private void colorPossibleMoves(ArrayList<Cell> moves, WindowController controller) {
        for (Cell move : moves)
            controller.colorCell(move, "red");
    }

    private void checkAndSetIfKingChecked(Cell source) {
        ArrayList<Cell> possibleMoves = source.getLegalMovesForPiece(this.board);
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

    private boolean doesMoveDoDiscoveryCheck(Cell destination) {
        this.board.setPiece(this.cellSelected.getRow(), this.cellSelected.getCol(), new VoidPiece()); // simule piece pas là
        ArrayList<Cell> allCellsForOppositColor = board.getAllCellsForColor(this.turn == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK);
        for (Cell cell:
             allCellsForOppositColor) {
            checkAndSetIfKingChecked(cell);
        }
        King king = (King) board.getCellByPiece(new King(this.turn), this.turn).getPiece();
        if(king.isCheck()) {
            king.setCheck(false, null);
            return true;
        }
        return false;


    }

    private void makeAMove(int row, int col, WindowController controller) throws IllegalMoveException {
        this.board.move(this.cellSelected, row, col);
        controller.moveImages(this.cellSelected, row, col);
        Cell destination = this.board.at(row, col);
        checkAndSetIfKingChecked(destination);
        this.cellSelected = null;
        this.nextTurn();
        this.changeTurn();
    }

    public boolean gameOver() {
        return false;
    }
}
