package model.element;

import model.carte.Axe;
import model.carte.Obstacle;
import model.carte.TypeAxe;

public class Montagne extends Obstacle {

    public Montagne(Axe axe) {
        super(axe);
    }

    @Override
    public TypeAxe getType() {
        return TypeAxe.MONTAGNE;
    }

}
