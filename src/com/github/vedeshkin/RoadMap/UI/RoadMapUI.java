package com.github.vedeshkin.RoadMap.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RoadMapUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


       Parent root =  FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
       Scene scene = new Scene(root);
       primaryStage.setScene(scene);
       primaryStage.show();

    }
}
