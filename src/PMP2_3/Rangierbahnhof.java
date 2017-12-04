package PMP2_3;

import java.util.StringJoiner;

public class Rangierbahnhof {

    int gleisAnzahl;
    Zug gleise[];

    Rangierbahnhof(int gleisAnzahl) {
        this.gleisAnzahl = gleisAnzahl;
        gleise = new Zug[10];
    }

    public synchronized void zugeinfahren(Zug zug, int gleisNummer) throws InterruptedException {
        while (gleise[gleisNummer] != null) {
            wait();
        }
        gleise[gleisNummer] = zug;
        notifyAll();
    }

    public synchronized Zug zugAusfahren(int gleisNummer) throws InterruptedException {
        while (gleise[gleisNummer] == null) {
            wait();
        }
        Zug ausfahrt = gleise[gleisNummer];
        gleise[gleisNummer] = null;
        notifyAll();
        return ausfahrt;
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
