package PMP2_1_1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class Sensor {

    private int id;
    private double messung;
    private String zeitstempel;

    private Collection<Sensor> messungen = new ArrayList<>();

    public Sensor(int id, double messung) {
        this.id = id;
        this.messung = messung;
        this.zeitstempel = LocalDateTime.now().toString();
    }

    public int getId() {
        return id;
    }

    public double getMessung() {
        return messung;
    }

    public String getZeitstempel() {
        return zeitstempel;
    }

    public void newMessung(double messung) {
        Sensor sensor = new Sensor(this.id, messung);
        messungen.add(sensor);
    }

    public Collection<Sensor> getMessungen() {
        return messungen;
    }

    @Override
    public String toString() {
        return "Messung: " + messung + "\tZeit: " + zeitstempel;
    }


}