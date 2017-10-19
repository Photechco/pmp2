package PMP2_1_2;

public class ArrayListe<T extends Comparable<T>> {

    private int anzahlElemente;
    private Object elemente[];

    public ArrayListe(){
        elemente = new Object[1];
        anzahlElemente = 0;
    }

    public void hinzufuegen(T element) {
        if(elemente.length == anzahlElemente-1) {
            System.arraycopy(elemente, 1, elemente, 1, elemente.length + 5);
        }
        elemente[anzahlElemente] = element;
        anzahlElemente++;
    }

    public Object get(int index) {
        return elemente[index];
    }

    public void entferne(T element) {
        int i = 0;
        for (Object oneElement:elemente) {
            if (oneElement == element) {
                entferneElementAnIndex(i);
            }
            i++;
        }
    }

    public void entferneElementAnIndex(int index) {
        elemente[index] = null;
        System.arraycopy(elemente,index,elemente,index-1,elemente.length);
        anzahlElemente--;
    }

    public int getAnzahlElemente() {
        return anzahlElemente;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object element:elemente) {
            sb.append(element.toString() + ", ");
        }
        return sb.toString();
    }

    Object getKleinstesElement(){
        Object kleinstesElement = get(0);

        for (Object element:elemente) {
            Comparable c = (Comparable) element;
            c.compareTo(kleinstesElement);
            kleinstesElement = element;
        }
        return kleinstesElement;
    }
}
