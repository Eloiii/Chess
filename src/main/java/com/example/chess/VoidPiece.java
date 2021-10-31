package com.example.chess;

import java.util.ArrayList;

public class VoidPiece implements Piece {
    public String toString() {
        return "";
    }

    @Override
    public ArrayList<Cell> getLegalMoves(int rowFrom, int colFrom, Board board) {
        return null;
    }

    @Override
    public char toChar() {
        return '.';
    }

    @Override
    public boolean isVoidPiece() {
        return true;
    }

    @Override
    public COLOR getColor() {
        return null;
    }
}
