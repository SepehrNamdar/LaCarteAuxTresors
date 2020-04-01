package model.element;

import model.carte.Axe;
import model.carte.Element;
import model.carte.TypeAxe;

public class Aventurier extends Element implements CanMove {

    private String name;
    private Orientation orientationDepart;

    public Aventurier(String name, Axe positionDepart, Orientation orientationDepart) {
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
        if (orientationDepart == Orientation.SUD) {
            axe = new Axe(axe.getAxeHorizontale(), axe.getAxeVerticale() + 1);
        } else if (orientationDepart == Orientation.NORD) {
            axe = new Axe(axe.getAxeHorizontale(), axe.getAxeVerticale() - 1);
        }
    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

    public void setAxe(Axe lastAxe) {
        super.axe = lastAxe;
    }
}
