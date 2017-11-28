package PMP2_2_2;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class Flugzeug extends Thread {
    private final Flughafen flughafen;
    private final Duration flugdauer = Duration.ofSeconds(ThreadLocalRandom.current().nextInt(10) + 1);
    private final Duration startZeit;
    private Duration zeit;
    private Status status = Status.IM_FLUG;

    public Flugzeug(Duration startZeit, Flughafen flughafen) {
        this.startZeit = startZeit;
        this.flughafen = flughafen;
        zeit = startZeit;

        loggeStatus();
    }

    @Override
    public synchronized void run() {
        // fliege
        while (zeitSeitStart().compareTo(flugdauer) < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        status = Status.IM_LANDEANFLUG;
        loggeStatus();

        // lande
        final Duration landeZeit = flugdauer.plusMillis(1500);
        while (zeitSeitStart().compareTo(landeZeit) < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        status = Status.GELANDET;
        loggeStatus();

        flughafen.meldeLandung();
    }

    public synchronized void meldeZeit(Duration zeit) {
        this.zeit = zeit;
        notify();
    }

    private Duration zeitSeitStart() {
        return zeit.minus(startZeit);
    }

    private void loggeStatus() {
        if (status == Status.IM_FLUG) {
            final long flugdauerSek = flugdauer.toMillis() / 1000;
            System.err.printf("Flugzeug (%s) ist %s, mit einer Flugdauer von %d s.%n",
                    getName(), status.getText(), flugdauerSek);
        } else {
            System.err.printf("Flugzeug (%s) ist %s.%n", getName(), status.getText());
        }
    }
}
