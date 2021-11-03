package com.example.chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PGNReader {

    public static void readPGN(String fileName) throws FileNotFoundException {
        Board board = Board.getInstance();
        File file = new File(fileName);
        System.out.println(file.exists());
        Scanner reader = new Scanner(file);
        while (reader.hasNext()) {
            String next = reader.next();
            if (next.matches("[0-9]*\\."))
                ;
            String whiteMove = reader.next();
            decode(whiteMove);
            String blackMove = reader.next();
        }
        reader.close();
    }

    private static void decode(String whiteMove) {
    }
}
