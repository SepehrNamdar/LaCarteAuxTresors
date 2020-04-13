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


    @Override
    public void avancer() {

    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

    @Override
    public void setAxe(Axe axe) {

    }

    @Override
    public void increaseNbTresor() {

    }

    @Override
    public String getSequencesMovement() {
        return "";
    }

    @Override
    protected void reduceNbTresor() {

    }
}
