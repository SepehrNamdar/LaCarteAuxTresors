package model.element;

import model.carte.Axe;

public abstract class Obstacle extends Element {

    public Obstacle(Axe axe) {
        super(axe);
    }

    @Override
    public int getNbTresor() {
        return 0;
    }

}
