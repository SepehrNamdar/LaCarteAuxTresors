package model.carte;

import java.util.List;

public class Carte {
    private Dimensions dimensions;
    private TypeAxe[][] plan;

    public Carte(Dimensions dimensions, List<Element> elements) {
        this.dimensions = dimensions;
        int nbCasesLargeur = dimensions.getLargeur();
        int nbCasesHauteur = dimensions.getHauteur();
        initPlan(nbCasesLargeur, nbCasesHauteur);
        placer(elements);
    }

    private void initPlan(int nbCasesLargeur, int nbCasesHauteur) {
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
            int positionDepartVerticale = element.getAxe().getAxeVerticale();
            int positionDepartHorizontale = element.getAxe().getAxeHorizontale();
            TypeAxe occupyingAxe = plan[positionDepartHorizontale][positionDepartVerticale];
            TypeAxe eltToPlaceType = element.getType();
            if (occupyingAxe == TypeAxe.PLAINE) {
                plan[positionDepartHorizontale][positionDepartVerticale] = eltToPlaceType;
            } else {
                throw new CanNotPlaceElementInMap(
                        eltToPlaceType, positionDepartHorizontale, positionDepartVerticale, occupyingAxe);
            }
        });
    }

    public int getLargeur() {
        return dimensions.getLargeur();
    }

    public int getHauteur() {
        return dimensions.getHauteur();
    }

    public TypeAxe getAxe(int axeHorizontale, int axeVerticale) {
        return plan[axeHorizontale][axeVerticale];
    }
}
