package model.carte;

import model.element.Aventurier;
import model.element.Tresor;

import java.util.List;

import static model.carte.TypeAxe.*;

public class Carte {
    private Dimensions dimensions;
    private List<Element> elements;

    private TypeAxe[][] plan;

    public Carte(Dimensions dimensions, List<Element> elements) {
        this.dimensions = dimensions;
        this.elements = elements;
        initPlan();
        placer(this.elements);
    }

    private void initPlan() {
        int nbCasesLargeur = dimensions.getLargeur();
        int nbCasesHauteur = dimensions.getHauteur();
        plan = new TypeAxe[nbCasesLargeur][nbCasesHauteur];
        for (int indexAxeVerticale = 0; indexAxeVerticale < nbCasesHauteur; indexAxeVerticale++) {
            for (int indexAxeHorizontale = 0; indexAxeHorizontale < nbCasesLargeur; indexAxeHorizontale++) {
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
        if (!getAxe(initialAventurierAxe.getAxeHorizontale(), initialAventurierAxe.getAxeVerticale()).equals(TRESOR)) {
            plan[initialAventurierAxe.getAxeHorizontale()][initialAventurierAxe.getAxeVerticale()] = PLAINE;
        }
        Axe aventurierAxe = aventurier.getAxe();
        if (!getAxe(aventurierAxe.getAxeHorizontale(), aventurierAxe.getAxeVerticale()).equals(TRESOR)) {
            plan[aventurierAxe.getAxeHorizontale()][aventurierAxe.getAxeVerticale()] = aventurier.getType();
        } else {
            for (Element elt : elements) {
                Axe axe = elt.getAxe();
                if (!axe.equals(aventurierAxe)) {
                    Tresor t = (Tresor) elt;
                    t.reduceNbTresor();
                }
            }
        }
    }

    private boolean isMoved(Aventurier aventurier) {
        return !isOutOfCarte(aventurier.getAxe()) && !isObstacle(aventurier.getAxe());
    }

    private boolean isOutOfCarte(Axe axe) {
        return axe.getAxeVerticale() >= dimensions.getHauteur() ||
                axe.getAxeHorizontale() >= dimensions.getLargeur() ||
                axe.getAxeHorizontale() < 0 || axe.getAxeVerticale() < 0;
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
