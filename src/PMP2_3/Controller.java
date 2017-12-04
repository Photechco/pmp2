package PMP2_3;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final List<Label> labelList = new ArrayList<>();
    private final List<ObservableList<Zugfuehrer>> waitingList = new ArrayList<>();

    @FXML
    private HBox gleis_section;

    @FXML
    private TextArea consol_section;

    @FXML
    private HBox waiting_section;


    public List<Label> getLabelList() {
        return labelList;
    }

    public List<ObservableList<Zugfuehrer>> getWaitingList() {
        return waitingList;
    }

    public TextArea getConsoleSection() {
        return consol_section;
    }

    /**
     * Initialisierung des GUI
     * Anzeigebereich für Gleise wird Dynamisch erzeugt.
     * Anzeigebereich für Warteschlange der Züge wird Dynamisch erzuegt.
     * Textfeld für Konsolenausgabe wird erzeugt.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Controller geladen!");

        for (int i = 0; i < Simulation.ANZAHL_GLEISE; i++) {

            //Initialisierung Gleisbereich

            Label label = new Label();
            label.setText("Gleis " + (i + 1));
            label.setPadding(new Insets(10, 10, 10, 10));
            label.setFont(new Font(20));
            label.setStyle("-fx-background-color: #ccddb2");

            gleis_section.getChildren().add(i, label);
            labelList.add(label);

            //Initialisierung Wartebereich

            ListView<Zugfuehrer> listView = new ListView<>();
            waiting_section.getChildren().add(i, listView);
            waitingList.add(listView.getItems());
        }
    }
}
