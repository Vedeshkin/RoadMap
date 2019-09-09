package com.github.vedeshkin.RoadMap.UI.controllers;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import com.github.vedeshkin.RoadMap.Core.RoadService;
import com.github.vedeshkin.RoadMap.Core.RoadServiceImpl;
import com.github.vedeshkin.RoadMap.DAO.City;
import com.github.vedeshkin.RoadMap.DAO.Road;
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

public class EditRoadController implements Initializable {

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
    private Road road;

    public EditRoadController(Road road) {
        this.road = road;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        City head = road.getHead();
        City tail = road.getTail();
        listOfCitiesObservable.setAll(cityService.getAllCitiesList());
        this.listOfRoadsFromUI.setItems(listOfCitiesObservable);
        this.listOfRoadsToUI.setItems(listOfCitiesObservable);

        this.listOfRoadsFromUI.getSelectionModel().select(head);
        this.listOfRoadsFromUI.setEditable(false);

        this.listOfRoadsToUI.getSelectionModel().select(tail);
        this.listOfRoadsToUI.setEditable(false);

        this.roadDistanceUI.setText(String.valueOf(road.getDistance()));
        this.roadNameUI.setText(road.getRoadName());


    }

    public void saveRoad(ActionEvent actionEvent) {
        String newRoadName = roadNameUI.getText();
        int newDistance = 0;
        City cityFrom = listOfRoadsFromUI.getSelectionModel().getSelectedItem();
        City cityTo = listOfRoadsToUI.getSelectionModel().getSelectedItem();
        if (cityFrom == null || cityTo == null) {
            System.out.println("Cities are not selected");
            return;
        }
        if (cityFrom == cityTo) {
            System.out.println("Circle roads are not allowed");
            return;
        }
        try {
            newDistance = Integer.parseInt(roadDistanceUI.getText());
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect number");
            return;
        }
        if (roadService.isRoadExist(newRoadName) && !road.getRoadName().equals(newRoadName)) {
            System.out.println("Road under name : " + newRoadName + " is already exists");
            return;
        }

        roadService.removeRoad(road.getRoadName());
        roadService.addRoad(newRoadName, newDistance, cityFrom, cityTo);
        RoadEditorController.updateListOfRoads();
        CityEditorController.updateListOfCitites();

        RoadMapUI.getCurrentStage().close();
    }

    public void closeDialog(ActionEvent actionEvent) {
        RoadMapUI.getCurrentStage().close();

    }

}
