package com.example.chess;

public enum BoardDimensions {
    MAX_COL(8), MAX_ROW(8);
    private final int value;

    BoardDimensions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}