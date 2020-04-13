package model.element;

public enum Movement {
    AVANCER("A"), GAUCHE("G"), DROITE("D");

    private String way;

    Movement(String way) {
        this.way = way;
    }

    public String getWay() {
        return way;
    }
}
