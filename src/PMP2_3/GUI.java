package PMP2_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controller zur aktualisierung der grafischen Benuzeroberfläche
 */

public class GUI extends Application {

    /**
     * Verwaltet die Gleisanzeigelabel
     */
    private static List<Label> gleisSection;

    /**
     * Verwaltet die Listview Elemente zur anzeige des Wartebereichs
     */
    private static List<ListView> waitSection;

    /**
     * speichert die aktuell laufende Simulation
     */
    private static Simulation simulation;

    /**
     * TextArea zur anzeige der Consolenausgabe
     */
    private static TextArea consol_Section;

    public static void main(String[] args) {
        simulation = new Simulation(10);
        Thread thread = new Thread(simulation);
        thread.start();
        launch(args);
    }

    /**
     * Lädt das GUI und weist Verbindungen zur GUI zu
     */

    @Override
    public void start(Stage primaryStage) {

        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("./BahnhofGUI.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gleisSection = Controller.getLabelList();
        consol_Section = Controller.getConsol_section();
        waitSection = Controller.getWaitingList();

    }

    /**
     * Anzeige des Gleises (belgt oder nicht Belegt wird aktualisiert).
     * Consolenbereich wird aktualisiert
     *
     * @param zug Zu behandelner Zug -> Ausgabe in der Console
     * @param gleis Gleis des Zuges -> Anzeige des Gleises
     * @param status Ein oder ausfahrt eines Zuges
     */

    public synchronized static void updateGUI(Zug zug, int gleis, boolean status) {

        Label label = gleisSection.get(gleis);
        if (status) {
            label.setStyle("-fx-background-color: red");
            consol_Section.appendText("Gleis " + (gleis + 1) + " einfahrt: " + zug + "\n");
        } else {
            label.setStyle("-fx-background-color: green");
            consol_Section.appendText("Gleis " + (gleis + 1) + " ausfahrt: " + zug + "\n");
        }
    }

    /**
     * Warteberich wird aktualisiert. Hinzufügen oder entfernen aus ViewList.
     * @param zugfuehrer zu behandelner Zuführer
     * @param status @True Hinzufügen zu wartebereich @False entfernen aus wartebereich
     */

    public synchronized static void updateWaiting(Zugfuehrer zugfuehrer, boolean status) {

        if (status) {
            waitSection.get(zugfuehrer.gleis).getItems().add(zugfuehrer);
        }
        else {
            waitSection.get(zugfuehrer.gleis).getItems().removeAll(zugfuehrer);
        }
    }

    /**
     * Lesen der Simulation
     * @return lesen der Simulation
     */

    public static Simulation getSimulation() {
        return simulation;
    }
}
