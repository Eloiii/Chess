package com.example.chess;

import java.util.ArrayList;

public class Knight implements Piece {

    private static final int[][] possibleCombinations = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    private final COLOR color;

    public Knight(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();
        for (int[] combination :
                possibleCombinations) {
            int rowTo = rowFrom + combination[0];
            int colTo = colFrom + combination[1];
            if (rowTo >= 0 && rowTo < BoardDimensions.MAX_ROW.getValue() && colTo >= 0 && colTo < BoardDimensions.MAX_COL.getValue()) {
                Cell cell = board.at(rowTo, colTo);
                results.add(cell);
            }
        }
        return results;
    }

    @Override
    public char toChar() {
        return 'N';
    }

    @Override
    public boolean isVoidPiece() {
        return false;
    }

    @Override
    public COLOR getColor() {
        return this.color;
    }
}
