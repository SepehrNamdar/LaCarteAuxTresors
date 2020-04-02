package model.element;

import model.carte.Axe;
import model.carte.Element;
import model.carte.TypeAxe;

import static model.carte.TypeAxe.TRESOR;

public class Tresor extends Element implements HavePoints {

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
    public void setNbTresor(int nbTresor) {
        this.nbTresor = nbTresor;
    }
}
