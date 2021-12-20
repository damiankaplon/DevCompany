package pl.damiankaplon.devcompany;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import java.io.IOException;

public class MainApplication extends Application {

    static { DbUtil.initialize();}

    public static final Logger logger = LogManager.getLogger();

    @Override
    public void start(Stage stage){
        FXMLLoader fxmlLoader1 = new FXMLLoader(MainApplication.class.getResource("RootView.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(MainApplication.class.getResource("HomeView.fxml"));
        BorderPane root = null;
        try {
            root = fxmlLoader1.load();
            logger.debug("RootView successfully loaded");
            root.setCenter(fxmlLoader2.load());
            logger.debug("HomeView successfully loaded");
        } catch (IOException e) {
            logger.error("Error while loading default main views. RootView and HomeView");
        }

        Scene scene = new Scene(root);
        stage.setTitle("Dev Company");
        stage.setScene(scene);
        stage.show();
        logger.info("App started");

    }

    public static void main(String[] args) {
        launch();
    }

}