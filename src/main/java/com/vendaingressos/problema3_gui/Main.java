package com.vendaingressos.problema3_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        // Load the root node from the FXML file
        VBox root = loader.load();

        // Set up the scene with the loaded FXML layout
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("JavaFX with FXML Layout");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
