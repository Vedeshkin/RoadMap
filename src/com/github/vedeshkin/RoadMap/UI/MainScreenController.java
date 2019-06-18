package com.github.vedeshkin.RoadMap.UI;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainScreenController  {

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
        if(city.isEmpty()){
            System.out.println("City is empty");
            return;
        }
        cityService.addCity(city,population);
        //huh?We fucked up.
        //We don't know if we added city or not and what is cause of the issue.

        cityNameTextField.clear();
        cityPopulationTextField.clear();


    }
}
