package model.carte;

import java.util.List;

public class Carte {
    private Dimensions dimensions;
    private TypeAxe[][] plan;

    public Carte(Dimensions dimensions, List<Element> elements) {
        this.dimensions = dimensions;
        int nbCasesLargeur = dimensions.getLargeur().getNbCases();
        int nbCasesHauteur = dimensions.getHauteur().getNbCases();
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
            int positionDepartVerticale = element.getAxe().getAxeVerticale().getNumCase();
            int positionDepartHorizontale = element.getAxe().getAxeHorizontale().getNumCase();
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

    public Largeur getLargeur() {
        return dimensions.getLargeur();
    }

    public Hauteur getHauteur() {
        return dimensions.getHauteur();
    }

    public TypeAxe getAxe(int axeHorizontale, int axeVerticale) {
        return plan[axeHorizontale][axeVerticale];
    }
}
