package PMP2_2_2;

public enum Status {
    IM_FLUG("im Flug"),
    IM_LANDEANFLUG("im Landeanflug"),
    GELANDET("gelandet");

    private final String text;

    Status(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
