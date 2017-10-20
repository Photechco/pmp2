package PMP2_1_2;

public class CheckErstesElement {
    public static void main (String[] ARGS) {
        ArrayListe arrayListe = new ArrayListe();
        arrayListe.hinzufuegen(5);
        Object check = arrayListe.get(0);

        if(check instanceof Number) {
            System.out.println("Erstes Element ist Zahl");
        }
        else {
            System.out.println("Erstes Element ist keine Zahl");
        }
        try {
            arrayListe.entferneElementAnIndex(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println("Erstes Element gelöscht");
    }
}
