package com.github.vedeshkin.RoadMap.UI.controllers;


import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import com.github.vedeshkin.RoadMap.DAO.City;
import com.github.vedeshkin.RoadMap.UI.RoadMapUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCityController implements Initializable {

    private City city;
    private CityService cityService = CityServiceImpl.getInstance();

    @FXML
    private TextField cityName;
    @FXML
    private TextField populationUI;


    public EditCityController(City city) {
        this.city = city;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityName.setText(city.getCityName());
        cityName.setEditable(false);
        populationUI.setText(String.valueOf(city.getPopulation()));
    }

    public void saveObject(ActionEvent event) {

        int populationValue = Integer.parseInt(populationUI.getText());
        city.setPopulation(populationValue);
        cityService.removeCity(city.getCityName());
        cityService.addCity(city.getCityName(), city.getPopulation());
        CityEditorController.updateListOfCitites();
        RoadMapUI.getCurrentStage().close();
    }

    public void closeDialog(ActionEvent actionEvent) {
        RoadMapUI.getCurrentStage().close();
    }
}
