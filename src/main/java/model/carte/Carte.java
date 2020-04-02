package model.carte;

import model.element.Aventurier;

import java.util.List;

import static model.carte.TypeAxe.MONTAGNE;
import static model.carte.TypeAxe.PLAINE;

public class Carte {
    private Dimensions dimensions;
    private TypeAxe[][] plan;

    public Carte(Dimensions dimensions, List<Element> elements) {
        this.dimensions = dimensions;
        initPlan();
        placer(elements);
    }

    private void initPlan() {
        int nbCasesLargeur = dimensions.getLargeur();
        int nbCasesHauteur = dimensions.getHauteur();
        plan = new TypeAxe[nbCasesLargeur][nbCasesHauteur];
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < nbCasesLargeur; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < nbCasesHauteur; indexAxeVerticale++) {
                plan[indexAxeHorizontale][indexAxeVerticale] = PLAINE;
            }
        }
    }

    private void placer(List<Element> elements) {
        elements.forEach(element ->
        {
            checkElementAxe(element.getAxe());
            int axeHorizontale = element.getAxe().getAxeHorizontale();
            int axeVerticale = element.getAxe().getAxeVerticale();
            TypeAxe occupyingAxe = plan[axeHorizontale][axeVerticale];
            TypeAxe eltToPlaceType = element.getType();
            if (occupyingAxe == PLAINE) {
                plan[axeHorizontale][axeVerticale] = eltToPlaceType;
            } else {
                throw new CanNotPlaceElementInMap(eltToPlaceType, element.getAxe(), eltToPlaceType);
            }
        });
    }

    private void checkElementAxe(Axe axe) {
        if (isOutOfCarte(axe)) {
            throw new CanNotPlaceElementInMap(axe);
        }
    }

    private boolean isOutOfCarte(Axe axe) {
        return axe.getAxeVerticale() >= dimensions.getHauteur() ||
                axe.getAxeHorizontale() >= dimensions.getLargeur() ||
                axe.getAxeHorizontale() < 0 || axe.getAxeVerticale() < 0;
    }

    public TypeAxe getAxe(int axeHorizontale, int axeVerticale) {
        return plan[axeHorizontale][axeVerticale];
    }

    public int getLargeur() {
        return dimensions.getLargeur();
    }

    public int getHauteur() {
        return dimensions.getHauteur();
    }

    public void avancer(Aventurier aventurier) {
        Axe initialAventurierAxe = aventurier.getAxe();
        aventurier.move();
        if (isMoved(aventurier)) {
            updatePlan(aventurier, initialAventurierAxe);
        } else {
            aventurier.setAxe(initialAventurierAxe);
        }
    }

    private void updatePlan(Aventurier aventurier, Axe initialAventurierAxe) {
        plan[initialAventurierAxe.getAxeHorizontale()][initialAventurierAxe.getAxeVerticale()] = PLAINE;
        plan[aventurier.getAxe().getAxeHorizontale()][aventurier.getAxe().getAxeVerticale()] = aventurier.getType();
    }

    private boolean isMoved(Aventurier aventurier) {
        return !isOutOfCarte(aventurier.getAxe()) && !isObstacle(aventurier.getAxe());
    }

    private boolean isObstacle(Axe axe) {
        return plan[axe.getAxeHorizontale()][axe.getAxeVerticale()] == MONTAGNE;
    }

    public void turnLeft(Aventurier aventurier) {
        aventurier.turnLeft();
    }

    public void turnRight(Aventurier laura) {
        laura.turnRight();
    }
}
