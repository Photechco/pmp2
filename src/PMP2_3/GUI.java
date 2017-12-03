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

public class GUI extends Application {

    private static List<Label> gleisSection;
    private static List<ListView> waitSection;
    private static Simulation simulation;
    private static TextArea consol_Section;

    public static void main(String[] args) {
        simulation = new Simulation(10);
        Thread thread = new Thread(simulation);
        thread.start();
        launch(args);
    }

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

    public synchronized static void updateWaiting(Zugfuehrer zugfuehrer, boolean status) {

        if (status) {
            waitSection.get(zugfuehrer.gleis).getItems().add(zugfuehrer);
        }
        else {
            waitSection.get(zugfuehrer.gleis).getItems().removeAll(zugfuehrer);
        }
    }

    public static Simulation getSimulation() {
        return simulation;
    }
}
