package model.element;

import model.carte.Axe;
import model.carte.TypeAxe;

import static model.carte.TypeAxe.TRESOR;

public class Tresor extends Element {

    private int nbTresor;

    public Tresor(Axe axe, int nbTresor) {
        super(axe);
        this.nbTresor = nbTresor;
    }

    @Override
    public TypeAxe getType() {
        return TRESOR;
    }

    @Override
    public int getNbTresor() {
        return nbTresor;
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
    public void reduceNbTresor() {
        if (nbTresor > 0) {
            nbTresor--;
        }
    }
}
