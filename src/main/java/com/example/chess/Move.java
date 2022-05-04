package com.example.chess;

public class Move {

    private String whiteMove;
    private String blackMove;

    public Move(String whiteMove) {
        this.whiteMove = whiteMove;
    }

    public String getWhiteMove() {
        return whiteMove;
    }

    public void setWhiteMove(String whiteMove) {
        this.whiteMove = whiteMove;
    }

    public String getBlackMove() {
        return blackMove;
    }

    public void setBlackMove(String blackMove) {
        this.blackMove = blackMove;
    }
}
