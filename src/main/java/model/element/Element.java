package model.element;

import model.carte.Axe;
import model.carte.TypeAxe;

import java.util.Objects;

import static model.carte.Carte.getTresors;
import static model.element.Movement.*;

public abstract class Element {
    public static final String MOVEMENT_SEPARATOR = "";
    protected Axe axe;

    public Element(Axe axe) {
        this.axe = axe;
    }

    public Axe getAxe() {
        return axe;
    }

    public abstract TypeAxe getType();

    public abstract int getNbTresor();

    public abstract void avancer();

    public abstract boolean canMove();

    public abstract void turnLeft();

    public abstract void turnRight();

    public abstract void setAxe(Axe axe);

    public abstract void increaseNbTresor();

    public abstract String getSequencesMovement();

    protected abstract void reduceNbTresor();

    public void move() {
        String[] movements = getSequencesMovement().split(MOVEMENT_SEPARATOR);
        for (String movement : movements) {
            if (AVANCER.getWay().equals(movement)) {
                avancer();
                getTresors().forEach(tresor -> {
                    if (getAxe().equals(tresor.getAxe())) {
                        tresor.reduceNbTresor();
                        this.increaseNbTresor();
                    }
                });
            } else if (DROITE.getWay().equals(movement)) {
                turnRight();
            } else if (GAUCHE.getWay().equals(movement)) {
                turnLeft();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(axe, element.axe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(axe);
    }
}
