package model.element;

public enum Orientation {
    NORD("N"), OUEST("O"), EST("E"), SUD("S");

    private final String name;

    Orientation(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
