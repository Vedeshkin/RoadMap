package com.github.vedeshkin.RoadMap.UI.controllers;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import com.github.vedeshkin.RoadMap.UI.RoadMapUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


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
        RoadMapUI.getCurrentStage().close();


    }

    public void closeDialog(ActionEvent actionEvent) {
        RoadMapUI.getCurrentStage().close();
    }


}
