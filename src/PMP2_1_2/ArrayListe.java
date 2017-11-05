package PMP2_1_2;


import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;

/**
 * Klasse kann Objekte speichern, verwalten und ausgeben.
 * Zu speicherndes Obket muss {@link Comparable} implementieren.
 * @param <T> Objekt muss {@link Comparable} implementieren
 */

public class ArrayListe<T extends Comparable<T>> {

    private int anzahlElemente;
    private Object elemente[];

    /**
     * Konstruktor der Arrayliste
     */

    public ArrayListe(){
        elemente = new Object[1];
        anzahlElemente = 0;
    }

    /**
     * Es wird das übergebene Objekt gespeichert.
     * Falls der das Array keinen Platz mehr hat, wird das Array erweitert.
     * @param element das element wir gespeichert
     */

    public void hinzufuegen(T element) {
        if(elemente.length == anzahlElemente-1) {
            System.arraycopy(elemente, 1, elemente, 1, elemente.length + 5);
            Arrays.copyOf(elemente, elemente.length);
        }
        elemente[anzahlElemente] = element;
        anzahlElemente++;
    }

    /**
     * Methode gibt das Objekt an Index zurück
     * @param index gibt den Index des zuückzugebenen Objektes an
     * @return gibt das Element an Index zurück
     */

    public Object get(int index) {
        return elemente[index];
    }

    /**
     * Methode löscht das übergebene Obkjekt aus dem Array.
     * Es wird über das Array iteriert und mit dem übergebenen Objekt übergeben.
     * Zum löschen wird die Funktion entferneElement aufgerufen.
     * @param element Übergebenes Objekt wird gelöscht
     */

    public void entferne(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Objekt darf nicht Null sein");
        }

        int i = 0;
        for (Object oneElement:elemente) {
            if (oneElement == element) {
                try {
                    entferneElementAnIndex(i);
                }
                catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }

    /**
     * Die Methode löscht das Element an index.
     * @param index Element an index wird gelöscht.
     */

    public void entferneElementAnIndex(int index)throws IllegalArgumentException {
        if (index > elemente.length) {
            throw new IllegalArgumentException("index zu hoch");
        }
        elemente[index] = null;
        if (anzahlElemente == 1) {
            anzahlElemente--;
        }else {
            System.arraycopy(elemente,index+1,elemente,index,elemente.length);
            anzahlElemente--;
        }
    }

    /**
     * Methode gibt die Anzahl der gespeicherten Elemente zurück.
     * @return Anzahl der gespeicherten Elemente Elemente
     */

    public int getAnzahlElemente() {
        return anzahlElemente;
    }

    /**
     * Methode liefert alle toString-Methoden, getrennt durch ein Komma, der gespeicherten Elelemente zurück.
     * @return toString aller gespeicherten Elemente
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object element:elemente) {
            sb.append(element.toString() + ", ");
        }
        return sb.toString();
    }

    /**
     * leifert das kleinste Element (verglichen durch compareTo())
     * @return das kleinste Element
     */

    Object getKleinstesElement(){
        Object kleinstesElement = get(0);

        for (Object element:elemente) {
            Comparable c = (Comparable) element;
            c.compareTo(kleinstesElement);
            kleinstesElement = element;
        }
        return kleinstesElement;
    }
}
