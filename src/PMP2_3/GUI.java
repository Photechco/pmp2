package PMP2_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application{

    public void startGui () {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("./BahnhofGUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);


            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
