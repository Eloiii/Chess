package com.example.chess;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("main_window.fxml"));
        Parent root = fxmlLoader.load();
        stage.getIcons().add(new Image(Objects.requireNonNull(Launcher.class.getResourceAsStream("logo.png"))));
        stage.setTitle("Echecs en gros");
        stage.setMinHeight(800);
        stage.setMinWidth(1108);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 1108, 800));
        stage.sizeToScene();
        stage.show();

        stage.setOnCloseRequest((event) -> {
            try {
                stop();
                Platform.exit();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}