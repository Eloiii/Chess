package com.example.chess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Objects;

public class WindowController {


    @FXML
    private GridPane grid;

    @FXML
    private GridPane infos;

    private Clock whiteClock;
    private Clock blackClock;

    private TableView<Move> table;

    public void initialize() {
        initGrid();
        initInfos();
    }

    private void initInfos() {
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHgrow(Priority.ALWAYS);
        infos.getColumnConstraints().add(colConstraints);

        initMovesGrid();
        initWhiteTimer();
        initBlackTimer();
        initMovesTable();
    }

    private void initMovesTable() {
        this.table = new TableView<>();

        TableColumn<Move, String> column1 = new TableColumn<>("White");
        column1.setCellValueFactory(new PropertyValueFactory<>("whiteMove"));
        column1.setPrefWidth(150);

        TableColumn<Move, String> column2 = new TableColumn<>("Black");
        column2.setCellValueFactory(new PropertyValueFactory<>("blackMove"));
        column2.setPrefWidth(150);

        this.table.getColumns().add(column1);
        this.table.getColumns().add(column2);

        this.table.setEditable(false);
        this.table.setPrefSize(300, 400);
        infos.add(this.table, 0, 1);

    }

    public void addMove(Move move) {
        if (move.getBlackMove() == null)
            this.table.getItems().add(move);
        else
            this.table.getItems().set(this.table.getItems().size() - 1, move);

    }

    private void initMovesGrid() {
        RowConstraints moves = new RowConstraints();
        moves.setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().add(moves);

        GridPane movesGrid = new GridPane();
        movesGrid.setPrefSize(300, 400);
        infos.add(movesGrid, 0, 1);
    }

    private void initBlackTimer() {
        RowConstraints timerBlack = new RowConstraints();
        timerBlack.setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().add(timerBlack);
        Label timerBlackText = setUpTimer();
        infos.add(timerBlackText, 0, 0);
        this.blackClock = new Clock(300, timerBlackText);
    }

    private void initWhiteTimer() {
        RowConstraints timerWhite = new RowConstraints();
        timerWhite.setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().add(timerWhite);
        Label timerWhiteText = setUpTimer();
        infos.add(timerWhiteText, 0, 2);
        this.whiteClock = new Clock(300, timerWhiteText);
        this.whiteClock.startTimer();
    }

    private Label setUpTimer() {
        Label timerText = new Label();
        timerText.setPrefSize(300, 200);
        timerText.setAlignment(Pos.CENTER);
        timerText.setFont(Font.font("Arial", 56));

        return timerText;
    }

    public void resumeWhiteTimer() {
        this.whiteClock.resume();
    }

    public void resumeBlackTimer() {
        this.blackClock.resume();
    }

    public void pauseWhiteClock() {
        this.whiteClock.pause();
    }

    public void pauseBlackClock() {
        this.blackClock.pause();
    }

    private void initGrid() {
        Board board = Board.getInstance();

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

        for (int i = 0; i < BoardDimensions.MAX_COL.getValue(); i++) {
            for (int j = 0; j < BoardDimensions.MAX_ROW.getValue(); j++) {
                addPane(i, j, board);
            }
        }


    }

    private void addPane(int colIndex, int rowIndex, Board board) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            try {
                Game.getInstance().selectCell(rowIndex, colIndex, this);
            } catch (IllegalMoveException | CheckException ex) {
                ex.printStackTrace();
            }
        });
        pane.setMaxSize(100, 100);
        pane.setMinSize(100, 100);
        if ((colIndex + rowIndex) % 2 == 1)
            pane.setStyle("-fx-background-color: #a16f5c");
        else
            pane.setStyle("-fx-background-color: #ecd3ba");
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

        }
        grid.add(pane, colIndex, rowIndex);
    }

    private void setImage(Pane pane, String img) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(img))));
        imageView.setCache(true);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);
        setImage(pane, imageView);
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

    public void moveImages(Cell from, Cell dest) {
        Pane paneFrom = (Pane) getNodeFromGridPane(from.getRow(), from.getCol());
        assert paneFrom != null;
        ImageView imageView = (ImageView) paneFrom.getChildren().get(0);

        Pane paneTo = (Pane) getNodeFromGridPane(dest.getRow(), dest.getCol());
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
                    colorCell(cell, "#a16f5c");
                else
                    colorCell(cell, "#ecd3ba");
            }
        }
    }
}
