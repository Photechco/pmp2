package PMP2_3;

import com.sun.javafx.collections.ObservableSequentialListWrapper;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Simulation extends Observable implements Runnable, Observer {
    public static final int ANZAHL_GLEISE = 1;
    private final Rangierbahnhof rangierbahnhof;

    Simulation () {
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getAnzahlGleise() {
        return rangierbahnhof.getGleisAnzahl();
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        if (!(o instanceof  Zugfuehrer))return;
        Zugfuehrer zugfuehrer = (Zugfuehrer) o;

        zugfuehrer.deleteObserver(this);

        setChanged();
        notifyObservers(zugfuehrer);
    }
}
