package model.element;

import model.carte.Axe;
import model.carte.Element;
import model.carte.TypeAxe;

import static model.carte.TypeAxe.AVENTURIER;
import static model.element.Orientation.NORD;
import static model.element.Orientation.SUD;

public class Aventurier extends Element implements CanMove {

    private String name;
    private Orientation currentOrientation;

    public Aventurier(String name, Axe positionDepart, Orientation orientationDepart) {
        super(positionDepart);
        this.name = name;
        this.currentOrientation = orientationDepart;
    }

    @Override
    public TypeAxe getType() {
        return AVENTURIER;
    }

    @Override
    public void move() {
        if (currentOrientation == SUD) {
            axe = new Axe(axe.getAxeHorizontale(), axe.getAxeVerticale() + 1);
        } else if (currentOrientation == NORD) {
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
