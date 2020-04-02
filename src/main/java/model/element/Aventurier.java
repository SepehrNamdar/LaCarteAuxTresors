package model.element;

import model.carte.Axe;
import model.carte.Element;
import model.carte.TypeAxe;

import static model.carte.TypeAxe.AVENTURIER;
import static model.element.Orientation.*;

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
        if (currentOrientation == NORD) {
            currentOrientation = OUEST;
        } else if (currentOrientation == OUEST) {
            currentOrientation = SUD;
        } else if (currentOrientation == SUD)  {
            currentOrientation = EST;
        } else if (currentOrientation == EST) {
            currentOrientation = NORD;
        }
    }

    @Override
    public void turnRight() {

    }

    public void setAxe(Axe lastAxe) {
        super.axe = lastAxe;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }
}
