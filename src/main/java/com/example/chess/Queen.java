package com.example.chess;

import java.util.ArrayList;

public class Queen implements Piece {

    private final COLOR color;

    public Queen(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        ArrayList<Cell> results = new ArrayList<>();
        ArrayList<Cell> bishopMovements = new Bishop(this.color).getLegalMoves(rowFrom, colFrom, board);
        ArrayList<Cell> rookMovements = new Rook(this.color).getLegalMoves(rowFrom, colFrom, board);
        results.addAll(bishopMovements);
        results.addAll(rookMovements);
        return results;
    }

    @Override
    public char toChar() {
        return 'Q';
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
