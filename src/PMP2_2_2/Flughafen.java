package PMP2_2_2;

import java.util.List;

public class Flughafen extends Thread {

    private int anzahlFlugzeuge;
    private List<Flugzeug> flugzeugList;

    public void Flughafen(int anzahlFlugzeuge) {
        for (int i=0; i<=anzahlFlugzeuge; i++) {
            flugzeugList.add(new Flugzeug(String.format("AB%03d",i),(int)(Math.random()*10),this));
            anzahlFlugzeuge++;
        }
    }

    @Override
    public void run() {
        boolean alive = true;
        int zeit = 0;
        Status istGelandet;

        while (!isInterrupted()) {

            flugzeugList.forEach(e->{
                if(e.istGelandet()) {
                    flugzeugList.remove(e);
                }
                e.setZeit(zeit);



            };)

            try {
                sleep(500);
            }
            catch (InterruptedException e) {
                interrupt();
            }
            zeit += 5;

        }
        while (alive) {
            flugzeugList.forEach(e->{
                try{ e.join();}
                catch (InterruptedException ex) {
                    e.interrupt();
                }
            });
            alive = false;
        }
    }


}
