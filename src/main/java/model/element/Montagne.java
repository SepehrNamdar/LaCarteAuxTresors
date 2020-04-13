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

}
