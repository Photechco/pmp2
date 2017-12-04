package PMP2_3;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class Zugfuehrer extends Observable implements Runnable {
    private final Rangierbahnhof rangierbahnhof;
    private final Aufgabe aufgabe;
    private final String zugId;
    private final int gleis;
    private boolean fertig;
    private String abschlussNachricht;
    Zugfuehrer(Rangierbahnhof rangierbahnhof) {
        this.rangierbahnhof = rangierbahnhof;
        aufgabe = ThreadLocalRandom.current().nextBoolean() ? Aufgabe.EINFAHREN : Aufgabe.AUSFAHREN;
        gleis = ThreadLocalRandom.current().nextInt(rangierbahnhof.getGleisAnzahl());
        zugId = "RB" + ThreadLocalRandom.current().nextInt(10, 100);
    }

    public int getGleis() {
        return gleis;
    }

    public Aufgabe getAufgabe() {
        return aufgabe;
    }

    public boolean istFertig() {
        return fertig;
    }

    public String getAbschlussNachricht() {
        return abschlussNachricht;
    }

    @Override
    public void run() {
        if (aufgabe == Aufgabe.EINFAHREN) {
            einfahren();
        } else if (aufgabe == Aufgabe.AUSFAHREN) {
            ausfahren();
        }

        fertig = true;
        setChanged();
        notifyObservers();
    }

    private void einfahren() {
        Zug zug = new Zug(zugId);

        try {
            rangierbahnhof.zugEinfahren(zug, gleis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        abschlussNachricht = "Gleis " + (gleis + 1) + " ist eingefahren: " + zug;
    }

    private void ausfahren() {
        Zug zug;
        try {
            zug = rangierbahnhof.zugAusfahren(gleis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        if (zug == null) {
            return;
        }

        abschlussNachricht = "Gleis " + (gleis + 1) + " ist ausgefahren " + zug;
    }

    @Override
    public String toString() {
        if (aufgabe == Aufgabe.EINFAHREN) {
            return "⬆ " + zugId;
        } else if (aufgabe == Aufgabe.AUSFAHREN) {
            return "⬇ wartet";
        }
        return "ungültige Aufgabe";
    }

    public enum Aufgabe {
        EINFAHREN, AUSFAHREN
    }
}
