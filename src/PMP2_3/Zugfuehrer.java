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
        int gleis = (int)(ThreadLocalRandom.current().nextDouble()*10);

        if (status) {
            Zug zug = new Zug("RB" + (int)(Math.random()*100));
            rangierbahnhof.zugeinfahren(zug, gleis);
            System.out.println("Gleis " + (gleis+1) + " ist eingefahren: " + zug.toString());
        }
        else {
            Zug zugausfahrt = rangierbahnhof.zugAusfahren(gleis);

            if (zugausfahrt != null) {
                System.out.println("Gleis " + (gleis+1) + " ist ausgefahren " + zugausfahrt);
            }
        }
    }
}
