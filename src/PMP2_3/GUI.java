package PMP2_3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Controller zur aktualisierung der grafischen Benuzeroberfläche
 */

public class GUI extends Application implements Observer {
    /**
     * speichert die aktuell laufende Simulation
     */
    private Simulation simulation;
    private Thread simlationThread;
    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        simulation = new Simulation();
        simulation.addObserver(this);
        simlationThread = new Thread(simulation);
    }

    /**
     * Lädt das GUI und weist Verbindungen zur GUI zu
     */
    @Override
    public void start(Stage primaryStage) {
        // load UI from XML
        BorderPane root;
        FXMLLoader loader = new FXMLLoader();
        try (InputStream stream = getClass().getResource("./BahnhofGUI.fxml").openStream()) {
            root = loader.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        controller = loader.getController();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // start simulation
        simlationThread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof Zugfuehrer)) return;
        Zugfuehrer zugfuehrer = (Zugfuehrer) arg;

        if (zugfuehrer.istFertig()) {
            Platform.runLater(() -> {
                // Gleise anpassen
                Label label = controller.getLabelList().get(zugfuehrer.getGleis());
                if (zugfuehrer.getAufgabe() == Zugfuehrer.Aufgabe.EINFAHREN) {
                    label.setStyle("-fx-background-color: #ffc4bc");
                } else {
                    label.setStyle("-fx-background-color: #ccddb2");
                }

                // Konsole
                controller.getConsoleSection().appendText(zugfuehrer.getAbschlussNachricht() + "\n");

                // Warteschlange
                controller.getWaitingList().get(zugfuehrer.getGleis()).remove(zugfuehrer);
            });
        } else {
            Platform.runLater(() -> {
                // Warteschlange
                controller.getWaitingList().get(zugfuehrer.getGleis()).add(zugfuehrer);
            });
        }
    }

    @Override
    public void stop() throws Exception {
        simulation.deleteObservers();
        simlationThread.interrupt();
        simulation.getRangierbahnhof().destroy();
    }
}
