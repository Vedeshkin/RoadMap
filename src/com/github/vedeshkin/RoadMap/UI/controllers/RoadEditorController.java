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

public class RoadEditorController implements Initializable {

    static RoadService roadService = RoadServiceImpl.getInstance();
    static CityService cityService = CityServiceImpl.getInstance();
    @FXML
    private Button closeWindowButton;
    @FXML
    private ListView<Road> listOfRoadsUI = new ListView<>();
    private static ObservableList<Road> listOfRoadsStorage = FXCollections.observableList(roadService.getAllRoadsList());

    public static void updateListOfRoads() {
        listOfRoadsStorage.setAll(roadService.getAllRoadsList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listOfRoadsUI.setItems(listOfRoadsStorage);
    }


    public void handleCloseWindow(ActionEvent actionEvent) {


        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();


    }


    public void addRoad(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditRoadDialog.fxml"));
        AddRoadController addRoadController = new AddRoadController();

        loader.setController(addRoadController);
        Node source = (Node) actionEvent.getSource();

        Parent child = loader.load();
        Scene addRoadScene = new Scene(child);
        Stage addRoadStage = new Stage();
        addRoadStage.initModality(Modality.WINDOW_MODAL);
        addRoadStage.initOwner(source.getScene().getWindow());
        addRoadStage.setScene(addRoadScene);
        RoadMapUI.setCurrentStage(addRoadStage);
        addRoadStage.show();


    }

    public void removeRoad(ActionEvent actionEvent) {

        Road roadToRemove = listOfRoadsUI.getSelectionModel().getSelectedItem();
        if (roadToRemove == null) {
            return;
        }
        City head = roadToRemove.getHead();
        City tail = roadToRemove.getTail();
        head.removeRoad(roadToRemove);
        tail.removeRoad(roadToRemove);
        cityService.flush();//We need to save the changes on disk, however it's dirty hack
        CityEditorController.updateListOfCitites();//just in case, i'm not sure if this is needed;
        roadService.removeRoad(roadToRemove.getRoadName());
        RoadEditorController.updateListOfRoads();


    }

    public void editRoad(ActionEvent actionEvent) throws IOException {

        Road roadToEdit = listOfRoadsUI.getFocusModel().getFocusedItem();
        if (roadToEdit == null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditRoadDialog.fxml"));
        EditRoadController editRoadController = new EditRoadController(roadToEdit);
        loader.setController(editRoadController);
        Node source = (Node) actionEvent.getSource();

        Parent child = loader.load();
        Scene editRoadScene = new Scene(child);
        Stage editRoadStage = new Stage();
        editRoadStage.initModality(Modality.WINDOW_MODAL);
        editRoadStage.initOwner(source.getScene().getWindow());
        editRoadStage.setScene(editRoadScene);
        RoadMapUI.setCurrentStage(editRoadStage);
        editRoadStage.show();


    }
}
