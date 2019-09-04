package com.github.vedeshkin.RoadMap.UI.controllers;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;


public class AddCityController {


    @FXML
    TextField cityName;
    @FXML
    TextField populationUI;
    TextFormatter<Integer> formatter;//need to find a way and test this usage.

    private CityService cityService = CityServiceImpl.getInstance();

    public void saveObject(ActionEvent event){

        String name  = cityName.getText();
        int population = Integer.parseInt(populationUI.getText());//quick and dirty
        if (cityService.isExist(name)) {
            System.out.println("The given city is already exist under this name");
            return;
        }

        cityService.addCity(name, population);
        CityEditorController.updateListOfCitites();
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();


    }

    public void closeDialog(ActionEvent actionEvent) {
        Node src = (Node) actionEvent.getSource();
        Stage st = (Stage) src.getScene().getWindow();
        st.close();
    }


}
