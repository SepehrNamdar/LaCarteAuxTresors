package model.element;

import model.carte.Axe;
import model.carte.Element;
import model.carte.TypeAxe;

public class Aventurier extends Element implements CanMove {

    private String name;
    private Orinetation orientationDepart;

    public Aventurier(String name, Axe positionDepart, Orinetation orientationDepart) {
        super(positionDepart);
        this.name = name;
        this.orientationDepart = orientationDepart;
    }

    @Override
    public TypeAxe getType() {
        return TypeAxe.AVENTURIER;
    }

    @Override
    public void move() {
        if (orientationDepart == Orinetation.SUD) {
            axe = new Axe(axe.getAxeHorizontale(), axe.getAxeVerticale() + 1);
        }
    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

}
