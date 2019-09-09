package com.github.vedeshkin.RoadMap.UI.controllers;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import com.github.vedeshkin.RoadMap.Core.RoadService;
import com.github.vedeshkin.RoadMap.Core.RoadServiceImpl;
import com.github.vedeshkin.RoadMap.DAO.City;
import com.github.vedeshkin.RoadMap.UI.RoadMapUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRoadController implements Initializable {

    @FXML
    ComboBox<City> listOfRoadsFromUI;
    @FXML
    ComboBox<City> listOfRoadsToUI;
    @FXML
    TextField roadNameUI;
    @FXML
    TextField roadDistanceUI;
    private static ObservableList<City> listOfCitiesObservable = FXCollections.observableArrayList();
    private static RoadService roadService = RoadServiceImpl.getInstance();
    private static CityService cityService = CityServiceImpl.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listOfCitiesObservable.setAll(cityService.getAllCitiesList());
        this.listOfRoadsFromUI.setItems(listOfCitiesObservable);
        this.listOfRoadsToUI.setItems(listOfCitiesObservable);
    }

    public void saveRoad(ActionEvent actionEvent) {
        /*
         * Quick and Dirty code I'm not sure if it works correct
         * TODO:
         *  refactor it?
         * */
        City fromCity = listOfRoadsFromUI.getSelectionModel().getSelectedItem();//
        City toCity = listOfRoadsToUI.getSelectionModel().getSelectedItem();
        if (fromCity == null || toCity == null) {
            System.out.println("City is null!");
            return;
        }
        if (fromCity == toCity) {
            //Some one is going to build are circle road?
            System.out.println("Circle road are not allowed");
            return;
        }
        String roadName = roadNameUI.getText();
        if (roadService.isRoadExist(roadName)) {
            System.out.println("The road under this name is already exists!");
            return;
        }
        int distance = 0;
        try {
            distance = Integer.parseInt(roadDistanceUI.getText());
        } catch (NumberFormatException ex) {
            System.out.println("Unable to parse int");
            return;
        }
        roadService.addRoad(roadName, distance, fromCity, toCity);
        RoadEditorController.updateListOfRoads();
        CityEditorController.updateListOfCitites();
        RoadMapUI.getCurrentStage().close();
    }

    public void closeDialog(ActionEvent actionEvent) {
        RoadMapUI.getCurrentStage().close();
    }
}
