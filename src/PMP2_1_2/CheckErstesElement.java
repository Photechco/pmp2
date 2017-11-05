package PMP2_1_2;

public class CheckErstesElement {
    public static void main (String[] ARGS) {
        ArrayListe arrayListe = new ArrayListe();
        arrayListe.hinzufuegen(1);
        arrayListe.hinzufuegen(2);
        arrayListe.hinzufuegen(3);
        arrayListe.hinzufuegen(4);

        arrayListe.entferneElementAnIndex(1);

        Object check = arrayListe.get(1);
        System.out.println(check);

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
