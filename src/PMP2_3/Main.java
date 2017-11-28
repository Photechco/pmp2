package PMP2_3;

public class Main implements Runnable {

    public static void main(String[] args) {


        Main main = new Main();
        Thread thread = new Thread(new Main());
        thread.start();


    }

    @Override
    public void run() {
        Rangierbahnhof rangierbahnhof = new Rangierbahnhof(10);

        while (!Thread.currentThread().isInterrupted()) {
            new Zugfuehrer(rangierbahnhof).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
