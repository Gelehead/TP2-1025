package client.JavaFx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            URL appResource = getClass().getResource("/AppTest.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(appResource);
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e ){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        launch();
    }

}