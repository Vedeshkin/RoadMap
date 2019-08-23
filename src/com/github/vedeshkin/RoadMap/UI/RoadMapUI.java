package com.github.vedeshkin.RoadMap.UI;

import com.github.vedeshkin.RoadMap.DAO.City;
import com.github.vedeshkin.RoadMap.sandbox.SandboxController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RoadMapUI extends Application {

    private static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        setMainStage(primaryStage);


    }
}
