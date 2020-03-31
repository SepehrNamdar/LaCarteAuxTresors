package model.carte;

public abstract class Element {
    protected Axe axe;

    public Element(Axe axe) {
        this.axe = axe;
    }

    public Axe getAxe() {
        return axe;
    }

    public abstract TypeAxe getType();

}
