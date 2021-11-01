package com.example.chess;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;

public class WindowController {

    @FXML
    private ImageView Bishop2Black;

    @FXML
    private ImageView Bishop2White;

    @FXML
    private ImageView Bishop5Black;

    @FXML
    private ImageView Bishop5White;

    @FXML
    private ImageView KingBlack;

    @FXML
    private ImageView KingWhite;

    @FXML
    private ImageView Knight1Black;

    @FXML
    private ImageView Knight1White;

    @FXML
    private ImageView Knight6Black;

    @FXML
    private ImageView Knight6White;

    @FXML
    private ImageView QueenBlack;

    @FXML
    private ImageView QueenWhite;

    @FXML
    private ImageView Rook0Black;

    @FXML
    private ImageView Rook0White;

    @FXML
    private ImageView Rook7Black;

    @FXML
    private ImageView Rook7White;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView pawn0Black;

    @FXML
    private ImageView pawn0White;

    @FXML
    private ImageView pawn1Black;

    @FXML
    private ImageView pawn1White;

    @FXML
    private ImageView pawn2Black;

    @FXML
    private ImageView pawn2White;

    @FXML
    private ImageView pawn3Black;

    @FXML
    private ImageView pawn3White;

    @FXML
    private ImageView pawn4Black;

    @FXML
    private ImageView pawn4White;

    @FXML
    private ImageView pawn5Black;

    @FXML
    private ImageView pawn5White;

    @FXML
    private ImageView pawn6Black;

    @FXML
    private ImageView pawn6White;

    @FXML
    private ImageView pawn7Black;

    @FXML
    private ImageView pawn7White;

    public void initialize() {

        for (int i = 0; i < BoardDimensions.MAX_COL.getValue(); i++) {
            for (int j = 0; j < BoardDimensions.MAX_ROW.getValue(); j++) {
                addPane(i, j);
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

    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex));
        pane.setMaxSize(100, 100);
        pane.setMinSize(100, 100);
        if ((colIndex + rowIndex) % 2 == 1)
            pane.setStyle("-fx-background-color: #4b7399");
        switch (rowIndex) {
            case 0:
                switch (colIndex) {
                    case 0:
                    case 7:
                        setImage(pane, "Rook_Black.png");
                        break;
                    case 1:
                    case 6:
                        setImage(pane, "Knight_Black.png");
                        break;
                    case 2:
                    case 5:
                        setImage(pane, "Bishop_Black.png");
                        break;
                    case 3:
                        setImage(pane, "Queen_Black.png");
                        break;
                    case 4:
                        setImage(pane, "King_Black.png");
                        break;
                }
                break;
            case 1:
                setImage(pane, "Pawn_Black.png");
                break;
            case 6:
                setImage(pane, "Pawn_White.png");
                break;
            case 7:
                switch (colIndex) {
                    case 0:
                    case 7:
                        setImage(pane, "Rook_White.png");
                        break;
                    case 1:
                    case 6:
                        setImage(pane, "Knight_White.png");
                        break;
                    case 2:
                    case 5:
                        setImage(pane, "Bishop_White.png");
                        break;
                    case 3:
                        setImage(pane, "Queen_White.png");
                        break;
                    case 4:
                        setImage(pane, "King_White.png");
                        break;
                }
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                break;
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
        pane.getChildren().add(imageView);
    }
}
