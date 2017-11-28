package PMP2_3;

public class Zug {

    public final String id;

    Zug(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
