package com.github.vedeshkin.RoadMap.sandbox;

import com.github.vedeshkin.RoadMap.DAO.City;
import com.github.vedeshkin.RoadMap.UI.controllers.AddCityController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SandboxUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditCityDialog.fxml"));
        City city = new City("Moscow", 12_000_000);
        AddCityController sandboxController = new AddCityController();
        loader.setController(sandboxController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
