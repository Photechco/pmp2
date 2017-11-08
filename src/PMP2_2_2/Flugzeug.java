package PMP2_2_2;

import java.time.LocalDateTime;

public class Flugzeug {
    private Flughafen flughafen;
    private String id;
    private int flugdauer;
    private int startzeit;
    private Status status;
    private int zeit;

    public void Flugzeug(String id, Flughafen flughafen) {
        this.id = id;
        this.flughafen = flughafen;
        status = Status.IM_FLUG;
        flugdauer = (int)(Math.random()*10);

    }
}
