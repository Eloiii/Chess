package com.example.chess;

public class Board {
    private static final int MAX_COL = 8;
    private static final int MAX_ROW = 8;
    private final Cell[][] cells;

    public Board() {
        this.cells = new Cell[MAX_ROW][MAX_COL];
    }

    public Cell at(int row, int col) throws IndexOutOfBoundsException {
        if (this.outOfBounds(row, col))
            throw new IndexOutOfBoundsException();
        return this.cells[row][col];
    }

    public void move(Cell from, int rowTo, int colTo) {
        if (legalMove(from, rowTo, colTo))
            System.out.println("Move legal");
        else
            System.out.println("Move pas legal");
    }

    public void setPiece(int row, int col, Piece piece) throws IndexOutOfBoundsException {
        if(this.outOfBounds(row, col))
            throw new IndexOutOfBoundsException();
        this.cells[row][col] = new Cell(row, col, piece);
    }

    private boolean legalMove(Cell cellFrom, int rowTo, int colTo) {
        Cell[] possibleMoves = cellFrom.getPiece().getLegalMoves(cellFrom);
        for (Cell currentMove : possibleMoves) {
            if (!this.outOfBounds(currentMove.getRow(), currentMove.getCol())) {
                if (currentMove.getCol() == colTo && currentMove.getRow() == rowTo)
                    return true;
            }
        }
        return false;
    }

    private boolean outOfBounds(int row, int col) {
        return !(row >= 0 && row < MAX_ROW && col >= 0 && col < MAX_COL);
    }

    public void initPieces() {
        for (int i = 0; i < MAX_COL; i++) {
            this.setPiece(0, i, new VoidPiece());
        }
        for (int i = 0; i < MAX_COL; i++) {
            this.setPiece(1, i, new Pawn(COLOR.BLACK));
        }
        for (int i = 0; i < MAX_COL; i++) {
            this.setPiece(2, i, new VoidPiece());
        }
        for (int i = 0; i < MAX_COL; i++) {
            this.setPiece(3, i, new VoidPiece());
        }
        for (int i = 0; i < MAX_COL; i++) {
            this.setPiece(4, i, new VoidPiece());
        }
        for (int i = 0; i < MAX_COL; i++) {
            this.setPiece(5, i, new VoidPiece());
        }
        for (int j = 0; j < MAX_COL; j++) {
            this.setPiece(6, j, new Pawn(COLOR.BLACK));
        }
        for (int i = 0; i < MAX_COL; i++) {
            this.setPiece(7, i, new VoidPiece());
        }
    }

    public void printBoard() {
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                System.out.print(this.cells[row][col].getPiece().toChar());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
