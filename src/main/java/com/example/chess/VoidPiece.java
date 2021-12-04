package com.example.chess;

import java.util.ArrayList;

public class VoidPiece implements Piece {
    public String toString() {
        return "";
    }

    @Override
    public ArrayList<Cell> getBasicMoves(Cell position) {
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

    /**
     * Get protected cells
     */
    @Override
    public ArrayList<Cell> getProtectedCells() {
        return null;
    }

    @Override
    public boolean isPinned() {
        return false;
    }
}
