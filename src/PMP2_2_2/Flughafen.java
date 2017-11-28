package PMP2_2_2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Flughafen extends Thread {
    private static final long ZEIT_SCHRITT_MS = 500;

    private final List<Flugzeug> flugzeuge = new ArrayList<>();
    private Duration zeit = Duration.ZERO;

    private Flughafen(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            flugzeuge.add(new Flugzeug(zeit, this));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Flughafen flughafen = new Flughafen(3);
        flughafen.start();
        flughafen.join();
    }

    @Override
    public void run() {
        // starte Flugzeuge
        for (Flugzeug flugzeug : flugzeuge) {
            flugzeug.start();
        }

        while (true) {
            // schreite Zeit voran
            zeit = zeit.plusMillis(ZEIT_SCHRITT_MS);
            try {
                Thread.sleep(ZEIT_SCHRITT_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            // melde Zeit an alle Flugzeuge
            synchronized (flugzeuge) {
                for (Flugzeug flugzeug : flugzeuge) {
                    flugzeug.meldeZeit(zeit);
                }
            }
        }
    }

    public void meldeLandung() {
        final Flugzeug flugzeug = new Flugzeug(zeit, this);
        flugzeug.start();
        synchronized (flugzeuge) {
            flugzeuge.add(flugzeug);
        }
    }
}
