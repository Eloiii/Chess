package com.example.chess;

import java.util.Scanner;

public class Game {
    private final Board board;
    private int turnNumber;
    private COLOR turn;

    public Game() {
        this.board = new Board();
        this.board.initPieces();
        this.turn = COLOR.WHITE;
    }

    private void changeTurn() {
        if (this.turn == COLOR.BLACK)
            this.turn = COLOR.WHITE;
        else
            this.turn = COLOR.BLACK;
    }

    public void nextTurn() {
        this.turnNumber++;
    }

    public Board getBoard() {
        return board;
    }

    public void startGame() throws IllegalMoveException {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver()) {
            this.board.printBoard();
            System.out.println("Move (rowFrom,colFrom rowTo,colTo): " + this.turn + " to play");
            String input = scanner.nextLine();
            Cell from = this.board.at(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(2)));
            this.board.move(from, Character.getNumericValue(input.charAt(4)), Character.getNumericValue(input.charAt(6)));
            this.nextTurn();
            this.changeTurn();
        }


    }

    public boolean gameOver() {
        return false;
    }
}
