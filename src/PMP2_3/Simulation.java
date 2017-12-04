package PMP2_3;

import java.util.Observable;
import java.util.Observer;

public class Simulation extends Observable implements Runnable, Observer {
    static final int ANZAHL_GLEISE = 6;
    private static final int DELAY_MS = 500;

    private final Rangierbahnhof rangierbahnhof;

    Simulation() {
        rangierbahnhof = new Rangierbahnhof(ANZAHL_GLEISE);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Zugfuehrer zugfuehrer = new Zugfuehrer(rangierbahnhof);

            setChanged();
            notifyObservers(zugfuehrer);

            zugfuehrer.addObserver(this);
            new Thread(zugfuehrer).start();

            try {
                Thread.sleep(DELAY_MS);
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Simulation unterbrochen!");
    }

    public Rangierbahnhof getRangierbahnhof() {
        return rangierbahnhof;
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        if (!(o instanceof Zugfuehrer)) return;
        Zugfuehrer zugfuehrer = (Zugfuehrer) o;

        zugfuehrer.deleteObserver(this);

        setChanged();
        notifyObservers(zugfuehrer);
    }
}
