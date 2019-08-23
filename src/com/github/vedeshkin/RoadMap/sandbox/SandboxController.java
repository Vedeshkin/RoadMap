package com.github.vedeshkin.RoadMap.sandbox;


import com.github.vedeshkin.RoadMap.DAO.City;
import com.sun.istack.internal.Nullable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SandboxController implements Initializable {

    private City city;

    @FXML
    private TextField cityName;
    @FXML
    private TextField population;


    public SandboxController(@Nullable City city) {
        this.city = city;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityName.setText(city.getCityName());
        population.setText(String.valueOf(city.getPopulation()));
    }
    public void saveObject(ActionEvent event){}
}
