package PMP2_3;

import javafx.application.Application;

public class Main implements Runnable {

    public static void main(String[] args) {
        Main main = new Main();
        Thread thread = new Thread(new Main());
        thread.start();
        GUI gui = new GUI();
        gui.startGui();
    }

    @Override
    public void run() {
        Rangierbahnhof rangierbahnhof = new Rangierbahnhof(10);

        while (!Thread.currentThread().isInterrupted()) {
            Zugfuehrer temp = new Zugfuehrer(rangierbahnhof);
            temp.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
