package model.carte;

public abstract class Obstacle {

    private Axe axe;

    public Obstacle(Axe axe) {
        this.axe = axe;
    }

    public Axe getAxe() {
        return axe;
    }
}
