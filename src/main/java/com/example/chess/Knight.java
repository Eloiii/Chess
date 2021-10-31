package com.example.chess;

import java.util.ArrayList;

public class Knight implements Piece {

    private final COLOR color;

    public Knight(COLOR color) {
        this.color = color;
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        return null;
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
