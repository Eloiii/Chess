package com.example.chess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;

public class WindowController {


    @FXML
    private GridPane grid;

    public void initialize() {
        Board board = Board.getInstance();

        for (int i = 0; i < BoardDimensions.MAX_COL.getValue(); i++) {
            for (int j = 0; j < BoardDimensions.MAX_ROW.getValue(); j++) {
                addPane(i, j, board);
            }
        }

        for (int i = 0; i < BoardDimensions.MAX_COL.getValue(); i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < BoardDimensions.MAX_ROW.getValue(); i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rowConstraints);
        }
    }

    private void addPane(int colIndex, int rowIndex, Board board) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            try {
                Game.getInstance().selectPiece(rowIndex, colIndex, this);
            } catch (IllegalMoveException ex) {
                ex.printStackTrace();
            }
        });
        pane.setMaxSize(100, 100);
        pane.setMinSize(100, 100);
        if ((colIndex + rowIndex) % 2 == 1)
            pane.setStyle("-fx-background-color: #4b7399");
        Piece piece = board.at(rowIndex, colIndex).getPiece();
        switch (piece.toChar()) {
            case 'P':
                if (piece.getColor() == COLOR.BLACK)
                    setImage(pane, "Pawn_Black.png");
                else
                    setImage(pane, "Pawn_White.png");
                break;
            case 'B':
                if (piece.getColor() == COLOR.BLACK)
                    setImage(pane, "Bishop_Black.png");
                else
                    setImage(pane, "Bishop_White.png");
                break;
            case 'R':
                if (piece.getColor() == COLOR.BLACK)
                    setImage(pane, "Rook_Black.png");
                else
                    setImage(pane, "Rook_White.png");
                break;
            case 'K':
                if (piece.getColor() == COLOR.BLACK)
                    setImage(pane, "King_Black.png");
                else
                    setImage(pane, "King_White.png");
                break;
            case 'Q':
                if (piece.getColor() == COLOR.BLACK)
                    setImage(pane, "Queen_Black.png");
                else
                    setImage(pane, "Queen_White.png");
                break;
            case 'N':
                if (piece.getColor() == COLOR.BLACK)
                    setImage(pane, "Knight_Black.png");
                else
                    setImage(pane, "Knight_White.png");
                break;
            default:
                System.err.println("INITALISATION PIECE INCONNUE");
        }
        grid.add(pane, colIndex, rowIndex);
    }

    private void setImage(Pane pane, String img) {
        URL input = WindowController.class.getResource(img);
        assert input != null;
        Image image = new Image(input.toString());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);
        if (pane.getChildren().size() >= 1)
            pane.getChildren().set(0, imageView);
        else
            pane.getChildren().add(imageView);
    }

    private void setImage(Pane pane, ImageView img) {
        if (pane.getChildren().size() >= 1)
            pane.getChildren().set(0, img);
        else
            pane.getChildren().add(img);
    }

    private Node getNodeFromGridPane(int row, int col) {
        for (Node node : this.grid.getChildren()) {
            if (node instanceof Pane && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void moveImages(Cell from, int rowTo, int colTo) {
        Pane paneFrom = (Pane) getNodeFromGridPane(from.getRow(), from.getCol());
        assert paneFrom != null;
        ImageView imageView = (ImageView) paneFrom.getChildren().get(0);

        Pane paneTo = (Pane) getNodeFromGridPane(rowTo, colTo);
        assert paneTo != null;
        this.setImage(paneTo, imageView);
        paneFrom.getChildren().clear();
    }

    public void colorCell(Cell cell, String color) {
        Pane pane = (Pane) getNodeFromGridPane(cell.getRow(), cell.getCol());
        assert pane != null;
        Platform.runLater(() -> pane.setStyle("-fx-background-color: " + color));
    }

    public void resetColors(Board board) {
        for (int i = 0; i < BoardDimensions.MAX_COL.getValue(); i++) {
            for (int j = 0; j < BoardDimensions.MAX_ROW.getValue(); j++) {
                Cell cell = board.at(j, i);
                if ((i + j) % 2 == 1)
                    colorCell(cell, "#4b7399");
                if ((i + j) % 2 == 0)
                    colorCell(cell, "white");

            }
        }
    }
}
