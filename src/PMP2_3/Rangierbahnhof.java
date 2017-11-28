package PMP2_3;

import java.util.StringJoiner;

public class Rangierbahnhof {
    int gleisAnzahl;
    Zug gleise[];

    Rangierbahnhof(int gleisAnzahl) {
        this.gleisAnzahl = gleisAnzahl;
        gleise = new Zug[10];
    }

    void zugeinfahren(Zug zug, int gleisNummer) {
        if (gleise[gleisNummer-1] != null) {

        }
        gleise[gleisNummer-1] = zug;
    }

    Zug zugAusfahren(int gleisNummer) {
        Zug ausfahrt = gleise[gleisNummer-1];
        gleise[gleisNummer-1] = null;
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
