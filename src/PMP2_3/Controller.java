package PMP2_3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Button button1 = new Button();
        gleis_section.getChildren().add(button1);
        Button button2 = new Button();
        gleis_section.getChildren().add(button2);
        Button button3 = new Button();
        gleis_section.getChildren().add(button3);


        System.out.println("Controller erzeugt");
    }

    @FXML
    private HBox gleis_section;


    @FXML
    private Pane consol_section;


    @FXML
    private HBox waiting_section;
}
