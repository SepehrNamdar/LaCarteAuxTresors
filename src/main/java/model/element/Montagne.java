package model.element;

import model.carte.Axe;
import model.carte.TypeAxe;

import static model.carte.TypeAxe.MONTAGNE;

public class Montagne extends Obstacle {

    public Montagne(Axe axe) {
        super(axe);
    }

    @Override
    public TypeAxe getType() {
        return MONTAGNE;
    }

    @Override
    public void move() {

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
    public void increaseNbtresor() {

    }

}
