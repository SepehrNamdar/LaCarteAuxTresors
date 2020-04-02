package model.element;

import model.carte.Axe;
import model.carte.Element;
import model.carte.TypeAxe;

import static model.carte.TypeAxe.TRESOR;

public class Tresor extends Element {

    public Tresor(Axe axe) {
        super(axe);
    }

    @Override
    public TypeAxe getType() {
        return TRESOR;
    }
}
