package PMP2_3;

import java.util.concurrent.ThreadLocalRandom;

public class Zugfuehrer extends Thread {

    int status;
    Rangierbahnhof rangierbahnhof;

    Zugfuehrer (Rangierbahnhof rangierbahnhof) {
        this.rangierbahnhof = rangierbahnhof;
    }

    @Override
    public void run() {

        boolean status =  ThreadLocalRandom.current().nextBoolean();

        if (status) {
            Zug zug = new Zug("RB" + (int)(Math.random()*100));
            rangierbahnhof.zugeinfahren(zug, (int)(Math.random()*10));
        }
        else {
            Zug zugausfahrt = rangierbahnhof.zugAusfahren((int)(Math.random()*10));
            System.out.println(zugausfahrt + "ist ausgefahren!");
        }
    }
}
