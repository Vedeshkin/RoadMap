package com.github.vedeshkin.RoadMap.sandbox;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class AnotherSandboxContoller {

    @FXML
    TextField cityName;
    @FXML
    TextField population;

    private CityService cityService = CityServiceImpl.getInstance();

    public void saveObject(ActionEvent event){

        String name  = cityName.getText();

        if (cityService.isExist(name)){
            System.out.println("The given city is already exist under this name");
            return;
        }
        cityService.addCity(name,10);
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();


    }

}
