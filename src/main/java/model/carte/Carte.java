package model.carte;

import model.element.Aventurier;

import java.util.List;

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
                plan[indexAxeHorizontale][indexAxeVerticale] = TypeAxe.PLAINE;
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
            if (occupyingAxe == TypeAxe.PLAINE) {
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
        int axeHorizontaleBeforeMovement = aventurier.getAxe().getAxeHorizontale();
        int axeVerticaleBeforeMovement = aventurier.getAxe().getAxeVerticale();
        aventurier.move();
        if (!isOutOfCarte(aventurier.getAxe())) {
            plan[axeHorizontaleBeforeMovement][axeVerticaleBeforeMovement] = TypeAxe.PLAINE;
            plan[aventurier.getAxe().getAxeHorizontale()][aventurier.getAxe().getAxeVerticale()] = aventurier.getType();
        }
    }
}
