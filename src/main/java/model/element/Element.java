package model.element;

import model.carte.Axe;
import model.carte.TypeAxe;

import java.util.Objects;

public abstract class Element {
    protected Axe axe;

    public Element(Axe axe) {
        this.axe = axe;
    }

    public Axe getAxe() {
        return axe;
    }

    public abstract TypeAxe getType();

    public abstract int getNbTresor();

    public abstract void move();

    public abstract boolean canMove();

    public abstract void turnLeft();

    public abstract void turnRight();

    public abstract void setAxe(Axe axe);

    public abstract void increaseNbtresor();

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
