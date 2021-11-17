package pl.damiankaplon.devcompany.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import lombok.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.damiankaplon.devcompany.MainApplication;

import java.io.IOException;

@Getter
@Setter
public class RootController {
    private Stage stage;
    private Scene scene;
    private BorderPane borderPane;

    @FXML
    public void switchToManagerView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ManagerView.fxml"));
        this.borderPane.setCenter(fxmlLoader.load());
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void show(){
        this.stage.show();
    }
}