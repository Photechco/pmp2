package PMP2_3;

import java.util.StringJoiner;

public class Rangierbahnhof {
    int gleisAnzahl;
    Zug gleise[];

    boolean running = true;

    Rangierbahnhof(int gleisAnzahl) {
        this.gleisAnzahl = gleisAnzahl;
        gleise = new Zug[gleisAnzahl];
    }

    public synchronized void zugEinfahren(Zug zug, int gleisNummer) throws InterruptedException {
        while (gleise[gleisNummer] != null) {
            wait();

            if (!running) {
                System.out.println("Einfahren abgebrochen!");
                return;
            }
        }
        gleise[gleisNummer] = zug;
        notifyAll();
    }

    public synchronized Zug zugAusfahren(int gleisNummer) throws InterruptedException {
        while (gleise[gleisNummer] == null) {
            wait();

            if (!running) {
                System.out.println("Ausfahren abgebrochen!");
                return null;
            }
        }
        Zug ausfahrt = gleise[gleisNummer];
        gleise[gleisNummer] = null;
        notifyAll();
        return ausfahrt;
    }

    public synchronized void destroy() {
        running = false;
        notifyAll();
    }

    public int getGleisAnzahl() {
        return gleisAnzahl;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(":", "[", "]");
        for (Zug zug : gleise) {
            stringJoiner.add(zug.toString());
        }

        return stringJoiner.toString();
    }
}
