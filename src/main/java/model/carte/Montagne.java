package model.carte;

public class Montagne extends Obstacle {

    public Montagne(Axe axe) {
        super(axe);
    }

    @Override
    public TypeAxe getType() {
        return TypeAxe.MONTAGNE;
    }
}
