package com.example.chess;

import java.util.ArrayList;

/**
 * Represents the board on which the game is played on
 * The Board class is a Singleton, one instance of the board is used here
 */
public class Board {
    /**
     * The one and only instance of the board
     */
    private static final Board INSTANCE = new Board();

    /**
     * Cells of the board
     */
    private final Cell[][] cells;

    /**
     * Create a new board, initializing the cells and the pieces on the board
     */
    private Board() {
        this.cells = new Cell[BoardDimensions.MAX_ROW.getValue()][BoardDimensions.MAX_COL.getValue()];
        this.fillCells();
        this.initPieces();
    }

    private void fillCells() {
        for (int row = 0; row < BoardDimensions.MAX_ROW.getValue(); row++) {
            for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
                this.cells[row][col] = new Cell(0, 0);
            }
        }
    }


    /**
     * Get the instance of the board
     *
     * @return the instance of the board
     */
    public static Board getInstance() {
        return INSTANCE;
    }

    /**
     * Get the cell at the given position
     *
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the cell at the given row and column
     * @throws IndexOutOfBoundsException catch illegal positions
     */
    public Cell at(int row, int col) throws IndexOutOfBoundsException {
        if (this.outOfBounds(row, col))
            throw new IndexOutOfBoundsException();
        return this.cells[row][col];
    }

    /**
     * Move a piece from a Cell to another
     *
     * @param from  the cell from where the piece is moved
     * @param dest the destination cell
     * @throws IllegalMoveException catch an illegal move
     */
    public void move(Cell from, Cell dest) throws IllegalMoveException {
        if (isALegalMove(from, dest)) {
            this.setPiece(new VoidPiece(), from.getRow(), from.getCol());
            this.setPiece(from.getPiece(), dest.getRow(), dest.getCol());
            System.out.println(moveToString(from, dest));
        } else
            throw new IllegalMoveException("Illegal move : " + from.getPiece().toChar() + " tried to move from " + from.getRow() + "," + from.getCol() + " to " + dest.getRow() + "," + dest.getCol());
    }

    /**
     * Get the move as a String
     *
     * @param source  the cell from where the piece is moved
     * @param dest the destination cell
     * @return the formated move
     */
    private String moveToString(Cell source, Cell dest) {
        String sourceCoo = source.coordinates();
        String destCoo = dest.coordinates();
        if(!dest.getPiece().isVoidPiece()) {
            if(source.getPiece() instanceof Pawn)
                return sourceCoo.charAt(0) + "x" +destCoo;
            else
                return source.getPiece().toChar() + "x" + destCoo;
        } else {
            if(source.getPiece() instanceof Pawn)
                return destCoo;
            else
                return source.getPiece().toChar() + destCoo;
        }
    }

    /**
     * Set a piece at a give postion
     *
     * @param piece the piece set at the postion
     * @param row the row of the destination cell
     * @param col the column of the destination cell
     * @throws IndexOutOfBoundsException catch illegal positions
     */
    public void setPiece(Piece piece, int row, int col) throws IndexOutOfBoundsException {
        if (this.outOfBounds(row, col))
            throw new IndexOutOfBoundsException();
        this.cells[row][col] = new Cell(row, col, piece);
    }

    /**
     * Check if a move is legal
     *
     * @param cellFrom the cell from where the piece is moved
     * @param dest the destination cell
     * @return true is the move is legal, false if it is illegal
     */
    private boolean isALegalMove(Cell cellFrom, Cell dest) {
        ArrayList<Cell> possibleMoves = Piece.getLegalMoves(cellFrom, null);
        for (Cell currentMove : possibleMoves) {
            if (!this.outOfBounds(currentMove.getRow(), currentMove.getCol())) {
                if (currentMove.getCol() == dest.getCol() && currentMove.getRow() == dest.getRow())
                    return true;
            }
        }
        return false;
    }

    /**
     * Check if a position if out of bounds of the board
     *
     *
     * @param row the row of the cell
     * @param col the column of the cell
     */
    private boolean outOfBounds(int row, int col) {
        return !(row >= 0 && row < BoardDimensions.MAX_ROW.getValue() && col >= 0 && col < BoardDimensions.MAX_COL.getValue());
    }

    /**
     * Initializes the piece for a new game
     */
    public void initPieces() {
        setVoidPieces();
        setPieces(0, COLOR.BLACK);
        setPieces(7, COLOR.WHITE);

    }

    /**
     * Initializes void pieces for a new game (row 2, 3, 4, 5)
     */
    private void setVoidPieces() {
        for (int row = 0; row < BoardDimensions.MAX_ROW.getValue(); row++) {
            for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
                this.setPiece(new VoidPiece(), row, col);
            }
        }

    }

    /**
     * Set pieces for the given row (0 for blacks, 7 for whites)
     *
     * @param row   the row
     * @param color the color of the pieces
     */
    private void setPieces(int row, COLOR color) {
        Piece piece = null;
        for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
            switch (col) {
                case 0:
                case 7:
                    piece = new Rook(color);
                    break;
                case 1:
                case 6:
                    piece = new Knight(color);
                    break;
                case 2:
                case 5:
                    piece = new Bishop(color);
                    break;
                case 3:
                    piece = new Queen(color);
                    break;
                case 4:
                    piece = new King(color);
                    break;
                default:
            }
            this.setPiece(piece, row, col);
        }
        int pawnRow = color == COLOR.BLACK ? 1 : 6;
        for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
            this.setPiece(new Pawn(color), pawnRow, col);
        }
    }

    /**
     * Display the board is ASCII characters
     */
    public void printBoard() {
        for (int row = 0; row < BoardDimensions.MAX_ROW.getValue(); row++) {
            for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
                System.out.print(this.cells[row][col].getPiece().toChar());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Get the cell for a give piece
     *
     * @param piece the piece
     * @param color the color of the piece
     * @return the cell where the piece stands
     */
    public Cell getCellByPiece(Piece piece, COLOR color) {
        for (int row = 0; row < BoardDimensions.MAX_ROW.getValue(); row++) {
            for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
                Piece pieceFor = this.at(row, col).getPiece();
                if (pieceFor.getClass() == piece.getClass() && pieceFor.getColor() == color)
                    return this.at(row, col);
            }
        }
        return null;
    }

    /**
     * Get all the cells where a piece of the given color stands on
     *
     * @param color the color
     * @return the list of cells for each piece of the color
     */
    public ArrayList<Cell> getAllCellsForColor(COLOR color) {
        ArrayList<Cell> res = new ArrayList<>();
        for (int row = 0; row < BoardDimensions.MAX_ROW.getValue(); row++) {
            for (int col = 0; col < BoardDimensions.MAX_COL.getValue(); col++) {
                Cell cell = this.at(row, col);
                if (cell.getPiece().getColor() == color)
                    res.add(cell);
            }
        }
        return res;
    }

    public ArrayList<Cell> getCellsBetween(Cell c1, Cell c2) {
        ArrayList<Cell> cellsBetweenc1Andc2 = new ArrayList<>();
        int diff_X = c1.getRow() - c2.getRow();
        int diff_Y = c1.getCol() - c2.getCol();
        int distance = c1.distanceFrom(c2);
        if (distance == 1)
            return cellsBetweenc1Andc2;
        double interval_X = diff_X / (distance + 1f);
        double interval_Y = diff_Y / (distance + 1f);
        for (int i = 1; i < distance; i++) {
            int x = (int) Math.floor(c2.getRow() + interval_X * i);
            int y = (int) Math.floor(c2.getCol() + interval_Y * i);
            Cell cell = this.at(x, y);
            cellsBetweenc1Andc2.add(cell);
        }
        return cellsBetweenc1Andc2;
    }

    /**
     * Check if a given cell is under attack by a piece from the given color
     *
     * @param cell  the cell
     * @param color the color attacking
     * @return true if the cell is under attack, false either
     */
    public boolean isUnderAttack(Cell cell, COLOR color) {
        ArrayList<Cell> allCellsOfPiecesForColor = this.getAllCellsForColor(color);
        ArrayList<Cell> possiblesMoves;
        for (Cell piece : allCellsOfPiecesForColor) {
            if(piece.getPiece() instanceof  King) {
                if(cell.distanceFrom(piece) <= 2)
                    return true;
                else continue;
            }
            if(piece.getPiece() instanceof Pawn)
                possiblesMoves = ((Pawn) piece.getPiece()).getDiagonalCells(this, piece, true);
            else
                possiblesMoves = Piece.getLegalMoves(piece, null);
            if (possiblesMoves.contains(cell))
                return true;
        }
        return false;
    }


}
