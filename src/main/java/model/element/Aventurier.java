package model.element;

import model.carte.Axe;
import model.carte.TypeAxe;

import static model.carte.Carte.getObstacles;
import static model.carte.Carte.getTresors;
import static model.carte.TypeAxe.AVENTURIER;
import static model.element.Orientation.*;

public class Aventurier extends Element {

    private final String name;
    private final String sequencesMouvement;
    private int nbTresor;
    private Orientation currentOrientation;

    public Aventurier(final String name,
                      final Axe axeDepart,
                      final Orientation orientationDepart,
                      final String sequencesMouvement) {
        super(axeDepart);
        this.name = name;
        this.currentOrientation = orientationDepart;
        this.sequencesMouvement = sequencesMouvement;
        nbTresor = 0;
    }

    @Override
    public TypeAxe getType() {
        return AVENTURIER;
    }

    @Override
    public int getNbTresor() {
        return nbTresor;
    }

    @Override
    public void avancer() {
        tryToMove();
        updateNbTresors();
    }

    private void tryToMove() {
        if (currentOrientation == SUD) {
            Axe axeToGo = new Axe(axe.getAxeHorizontal(), axe.getAxeVertical() + 1);
            move(axeToGo);
        } else if (currentOrientation == NORD) {
            Axe axeToGo = new Axe(axe.getAxeHorizontal(), axe.getAxeVertical() - 1);
            move(axeToGo);
        } else if (currentOrientation == EST) {
            Axe axeToGo = new Axe(axe.getAxeHorizontal() + 1, axe.getAxeVertical());
            move(axeToGo);
        } else if (currentOrientation == OUEST) {
            Axe axeToGo = new Axe(axe.getAxeHorizontal() - 1, axe.getAxeVertical());
            move(axeToGo);
        }
    }

    private void updateNbTresors() {
        getTresors().forEach(tresor -> {
            if (getAxe().equals(tresor.getAxe())) {
                tresor.reduceNbTresor();
                this.increaseNbTresor();
            }
        });
    }

    private void move(final Axe axeToGo) {
        if (!axeToGo.isOutOfCarte()) {
            if (getObstacles().isEmpty()) {
                axe = axeToGo;
            } else {
                moveIfThereIsNoObstacle(axeToGo);
            }
        }
    }

    private void moveIfThereIsNoObstacle(final Axe axeToGo) {
        if (isNotAnyObstacle(axeToGo)) {
            axe = axeToGo;
        }
    }

    private boolean isNotAnyObstacle(Axe axeToGo) {
        return getObstacles().stream().noneMatch(obstacle -> obstacle.getAxe().equals(axeToGo));
    }

    @Override
    public void turnLeft() {
        if (currentOrientation == NORD) {
            currentOrientation = OUEST;
        } else if (currentOrientation == OUEST) {
            currentOrientation = SUD;
        } else if (currentOrientation == SUD)  {
            currentOrientation = EST;
        } else if (currentOrientation == EST) {
            currentOrientation = NORD;
        }
    }

    @Override
    public void turnRight() {
        if (currentOrientation == NORD) {
            currentOrientation = EST;
        } else if (currentOrientation == EST) {
            currentOrientation = SUD;
        } else if (currentOrientation == SUD)  {
            currentOrientation = OUEST;
        } else if (currentOrientation == OUEST) {
            currentOrientation = NORD;
        }
    }

    public void setAxe(final Axe lastAxe) {
        super.axe = lastAxe;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    public void increaseNbTresor() {
        nbTresor++;
    }

    @Override
    public String getSequencesMovement() {
        return sequencesMouvement;
    }

    @Override
    protected void reduceNbTresor() {

    }

    public String getName() {
        return name;
    }
}
