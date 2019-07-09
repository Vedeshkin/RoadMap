package com.github.vedeshkin.RoadMap.UI;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private Button addCityButton;
    @FXML
    private TextField cityNameTextField;
    @FXML
    private TextField cityPopulationTextField;
    private CityService cityService = CityServiceImpl.getInstance();


    public void handleAddCityButton(ActionEvent actionEvent) {
        String city = cityNameTextField.getText();
        int population = Integer.parseInt(cityPopulationTextField.getText());
        if (city.isEmpty()) {
            System.out.println("City is empty");
            return;
        }
        cityService.addCity(city, population);
        //huh?We fucked up.
        //We don't know if we added city or not and what is cause of the issue.
        //TODO:
        /*Bad design of the city service
         * We need to refactor it asap
         * */

        cityNameTextField.clear();
        cityPopulationTextField.clear();


    }

    public void handleManageCities(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/CityEditor.fxml"));
        Scene cityEditorScene = new Scene(root);
        Stage cityEditorStage =  new Stage();
        cityEditorStage.initModality(Modality.WINDOW_MODAL);
        cityEditorStage.initOwner(RoadMapUI.getMainStage());
        cityEditorStage.setScene(cityEditorScene);
        cityEditorStage.show();


    }

    public void handleManageRoads(ActionEvent actionEvent) {
        /*
         * Here we should show the modal windows within the list of the roads
         * Also please remember that a road must be connected to the cities

         */
        System.out.println("Under development :(");
    }
}
