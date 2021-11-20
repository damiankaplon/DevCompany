package pl.damiankaplon.devcompany.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import lombok.*;
import pl.damiankaplon.devcompany.MainApplication;

import java.io.IOException;

@Getter
@Setter
public class RootController {
    //private Stage stage;
    //private Scene scene;
    @FXML private BorderPane borderPane;

    @FXML
    public void switchToManagerView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ClientsView.fxml"));
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