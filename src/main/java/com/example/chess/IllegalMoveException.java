package com.example.chess;

public class IllegalMoveException extends Exception {
    public IllegalMoveException(String s) {
        super(s);
    }
}
