package model.element;

import model.carte.Axe;
import model.carte.TypeAxe;

import static model.carte.TypeAxe.AVENTURIER;
import static model.element.Orientation.*;

public class Aventurier extends Element {

    private final String name;
    private int nbTresor;
    private Orientation currentOrientation;

    public Aventurier(String name, Axe positionDepart, Orientation orientationDepart) {
        super(positionDepart);
        this.name = name;
        this.currentOrientation = orientationDepart;
        nbTresor = 0;
    }

    @Override
    public TypeAxe getType() {
        return AVENTURIER;
    }

    @Override
    public int getNbTresor() {
        return nbTresor;
    }

    @Override
    public void move() {
        if (currentOrientation == SUD) {
            axe = new Axe(axe.getAxeHorizontal(), axe.getAxeVertical() + 1);
        } else if (currentOrientation == NORD) {
            axe = new Axe(axe.getAxeHorizontal(), axe.getAxeVertical() - 1);
        } else if (currentOrientation == EST) {
            axe = new Axe(axe.getAxeHorizontal() + 1, axe.getAxeVertical());
        } else if (currentOrientation == OUEST) {
            axe = new Axe(axe.getAxeHorizontal() - 1, axe.getAxeVertical());
        }
    }

    @Override
    public boolean canMove() {
        return true;
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
        if (currentOrientation == NORD) {
            currentOrientation = EST;
        } else if (currentOrientation == EST) {
            currentOrientation = SUD;
        } else if (currentOrientation == SUD)  {
            currentOrientation = OUEST;
        } else if (currentOrientation == OUEST) {
            currentOrientation = NORD;
        }
    }

    public void setAxe(Axe lastAxe) {
        super.axe = lastAxe;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    public void increaseNbtresor() {
        nbTresor++;
    }

    public String getName() {
        return name;
    }
}
