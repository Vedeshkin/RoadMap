package com.github.vedeshkin.RoadMap.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CityEditorController {

    @FXML
    public Button closeWindowButton;


    public void handleCloseWindow(ActionEvent actionEvent) {


        Node source  = (Node) actionEvent.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();



    }
}
