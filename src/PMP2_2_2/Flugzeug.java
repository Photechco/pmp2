package PMP2_2_2;


public class Flugzeug extends Thread{

    private Flughafen flughafen;
    private String id;
    private int flugdauer;
    private int startzeit;
    private Status status;
    private int zeit = 0;

    public Flugzeug(String id, int flugdauer, Flughafen flughafen, int zeit) {
        this.id = id;
        this.flughafen = flughafen;
        status = Status.IM_FLUG;
        this.flugdauer = flugdauer;
        this.startzeit = zeit;
        status = Status.IM_FLUG;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {



        }
    }

    public boolean istGelandet(){


    }

    @Override
    public String toString() {
        return id + startzeit + status;
    }

    public void setZeit(int zeit) {
        this.zeit = zeit - this.startzeit;
        flugdauer = this.zeit - startzeit;
    }
}
