package pl.damiankaplon.devcompany;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.damiankaplon.devcompany.controller.RootController;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("RootView.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Dev Company");
        stage.setScene(scene);
        RootController rootController = fxmlLoader.getController();
        rootController.setBorderPane(root);
        rootController.setScene(scene);
        rootController.setStage(stage);
        rootController.show();
    }

    public static void main(String[] args) {
        launch();
    }
}