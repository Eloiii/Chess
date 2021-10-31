package com.example.chess;

public class Game {
    private Board board;
    private int turnNumber;
    private COLOR turn;

    public Game() {
        this.board = new Board();
        this.board.initPieces();
    }

    private void changeTurn() {
        if(this.turn == COLOR.BLACK)
            this.turn = COLOR.WHITE;
        else
            this.turn =  COLOR.BLACK;
    }

    public void nextTurn() {
        this.turnNumber++;
    }

    public Board getBoard() {
        return board;
    }
}
