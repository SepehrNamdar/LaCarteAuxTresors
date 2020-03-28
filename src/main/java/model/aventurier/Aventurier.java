package model.aventurier;

import model.carte.Axe;

public class Aventurier {

    private String name;
    private Axe positionDepart;

    public Aventurier(String name, Axe positionDepart) {
        this.name = name;
        this.positionDepart = positionDepart;
    }

    public String getName() {
        return name;
    }

    public Axe getPositionDepart() {
        return positionDepart;
    }
}
