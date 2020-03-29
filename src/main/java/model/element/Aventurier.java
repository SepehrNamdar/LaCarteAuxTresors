package model.element;

import model.carte.Axe;
import model.carte.Element;
import model.carte.TypeAxe;

public class Aventurier extends Element {

    private String name;

    public Aventurier(String name, Axe positionDepart) {
        super(positionDepart);
        this.name = name;
    }

    @Override
    public TypeAxe getType() {
        return TypeAxe.AVENTURIER;
    }
}
