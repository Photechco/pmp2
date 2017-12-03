package PMP2_3;

import java.util.concurrent.ThreadLocalRandom;

public class Zugfuehrer extends Thread {

    private Rangierbahnhof rangierbahnhof;
    public String id;
    public int gleis;


    Zugfuehrer (Rangierbahnhof rangierbahnhof) {
        this.rangierbahnhof = rangierbahnhof;
    }

    @Override
    public void run() {

        boolean status =  ThreadLocalRandom.current().nextBoolean();
        gleis = (int)(ThreadLocalRandom.current().nextDouble()*10);

        if (status) {
            id = "RB" + (int)(Math.random()*100);
            Zug zug = new Zug(id);
            GUI.updateWaiting(this,true);
            rangierbahnhof.zugeinfahren(zug, gleis);
            GUI.updateGUI(zug, gleis, status);
            GUI.updateWaiting(this,false);
            System.out.println("Gleis " + (gleis+1) + " ist eingefahren: " + zug);

        }
        else {
            Zug zug = rangierbahnhof.zugAusfahren(gleis);

            if (zug != null) {

                GUI.updateGUI(zug, gleis, status);
                System.out.println("Gleis " + (gleis+1) + " ist ausgefahren " + zug);
            }
        }
    }

    @Override
    public String toString() {
        return id;
    }
}
