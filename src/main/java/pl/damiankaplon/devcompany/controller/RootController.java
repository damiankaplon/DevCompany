package pl.damiankaplon.devcompany.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import lombok.*;
import pl.damiankaplon.devcompany.MainApplication;
import pl.damiankaplon.devcompany.service.ClientService;

import java.io.IOException;

@Getter
@Setter
public class RootController {
    //private Stage stage;
    //private Scene scene;
    @FXML private BorderPane borderPane;

    @FXML
    public void switchToClientView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ClientsView.fxml"));
        new ClientService().testSaveData();
        this.borderPane.setCenter(fxmlLoader.load());
    }

    @FXML
    public void switchToHomeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("HomeView.fxml"));
        this.borderPane.setCenter(fxmlLoader.load());
    }

    @FXML
    public void switchToWorkersMachinesView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("WorkersMachinesView.fxml"));
        this.borderPane.setCenter(fxmlLoader.load());
    }
}