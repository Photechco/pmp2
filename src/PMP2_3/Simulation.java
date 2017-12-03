package PMP2_3;

public class Simulation implements Runnable {

    private int anzahlGleise;

    Simulation (int anzahlGleise) {
        this.anzahlGleise = anzahlGleise;
    }

    @Override
    public void run() {

        Rangierbahnhof rangierbahnhof = new Rangierbahnhof(anzahlGleise);

        while (!Thread.currentThread().isInterrupted()) {
            Zugfuehrer zugfuehrer = new Zugfuehrer(rangierbahnhof);
            zugfuehrer.start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getAnzahlGleise() {
        return anzahlGleise;
    }

    public void setAnzahlGleise(int anzahlGleise) {
        this.anzahlGleise = anzahlGleise;
    }
}
