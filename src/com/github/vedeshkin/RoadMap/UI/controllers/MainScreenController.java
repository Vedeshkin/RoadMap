package com.github.vedeshkin.RoadMap.UI.controllers;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import com.github.vedeshkin.RoadMap.UI.RoadMapUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private Button addCityButton;
    @FXML
    private TextField cityNameTextField;
    @FXML
    private TextField cityPopulationTextField;
    private CityService cityService = CityServiceImpl.getInstance();

    public void handleManageCities(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/CityEditor.fxml"));
        Scene cityEditorScene = new Scene(root);
        Stage cityEditorStage =  new Stage();
        cityEditorStage.initModality(Modality.WINDOW_MODAL);
        cityEditorStage.initOwner(RoadMapUI.getCurrentStage());
        cityEditorStage.setScene(cityEditorScene);
        cityEditorStage.show();


    }

    public void handleManageRoads(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/RoadEditor.fxml"));
        Scene roadEditorScene = new Scene(root);
        Stage roadEditorStage = new Stage();
        roadEditorStage.initModality(Modality.WINDOW_MODAL);
        roadEditorStage.initOwner(RoadMapUI.getCurrentStage());
        roadEditorStage.setScene(roadEditorScene);
        roadEditorStage.show();
    }

    public void handleExit(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage st = (Stage) source.getScene().getWindow();
        st.close();
    }
}
