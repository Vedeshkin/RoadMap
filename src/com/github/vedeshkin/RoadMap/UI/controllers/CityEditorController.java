package com.github.vedeshkin.RoadMap.UI.controllers;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import com.github.vedeshkin.RoadMap.DAO.City;
import com.github.vedeshkin.RoadMap.UI.RoadMapUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CityEditorController implements Initializable {

    static CityService cityService = CityServiceImpl.getInstance();
    @FXML
    private Button closeWindowButton;
    @FXML
    private ListView<City> listOfCitiesUI = new ListView<>();
    private static ObservableList<City> listOfCitiesStorage = FXCollections.observableList(cityService.getAllCitiesList());

    public static void updateListOfCitites() {
        listOfCitiesStorage.setAll(cityService.getAllCitiesList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listOfCitiesUI.setItems(listOfCitiesStorage);
    }


    public void handleCloseWindow(ActionEvent actionEvent) {


        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();


    }


    public void handleAddCity(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditCityDialog.fxml"));
        AddCityController addCityController = new AddCityController();
        loader.setController(addCityController);
        Node source = (Node) actionEvent.getSource();

        Parent child = loader.load();
        Scene addCityScene = new Scene(child);
        Stage addCityStage = new Stage();
        addCityStage.initModality(Modality.WINDOW_MODAL);
        addCityStage.initOwner(source.getScene().getWindow());
        addCityStage.setScene(addCityScene);
        RoadMapUI.setCurrentStage(addCityStage);
        addCityStage.show();


    }

    public void handleRemoveCity(ActionEvent actionEvent) {

        City cityToRemove = listOfCitiesUI.getFocusModel().getFocusedItem();
        cityService.removeCity(cityToRemove.getCityName());
        listOfCitiesStorage.setAll(cityService.getAllCitiesList());

    }

    public void handleEditCity(ActionEvent actionEvent) throws IOException {

        City cityToEdit = listOfCitiesUI.getFocusModel().getFocusedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditCityDialog.fxml"));
        EditCityController editCityController = new EditCityController(cityToEdit);
        loader.setController(editCityController);
        Node source = (Node) actionEvent.getSource();

        Parent child = loader.load();
        Scene editCityScene = new Scene(child);
        Stage editCityStage = new Stage();
        editCityStage.initModality(Modality.WINDOW_MODAL);
        editCityStage.initOwner(source.getScene().getWindow());
        editCityStage.setScene(editCityScene);
        RoadMapUI.setCurrentStage(editCityStage);
        editCityStage.show();


    }
}
