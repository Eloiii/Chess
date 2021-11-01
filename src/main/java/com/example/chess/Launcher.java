package com.example.chess;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    public static void main(String[] args) throws IllegalMoveException {
        launch();
        Game game = new Game();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("main_window.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Echecs en gros");
        stage.setMinHeight(800);
        stage.setMinWidth(800);
        stage.setScene(new Scene(root, 800, 800));
        stage.sizeToScene();
        stage.show();

        stage.setOnCloseRequest((event) -> {
            try {
                stop();
                Platform.exit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}