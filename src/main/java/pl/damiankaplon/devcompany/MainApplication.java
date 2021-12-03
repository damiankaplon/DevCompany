package pl.damiankaplon.devcompany;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.damiankaplon.devcompany.dbutil.DbUtil;

import java.io.IOException;

public class MainApplication extends Application {

    static { DbUtil.initialize();}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(MainApplication.class.getResource("RootView.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(MainApplication.class.getResource("HomeView.fxml"));
        BorderPane root = fxmlLoader1.load();
        root.setCenter(fxmlLoader2.load());
        Scene scene = new Scene(root);
        stage.setTitle("Dev Company");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}