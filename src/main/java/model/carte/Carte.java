package model.carte;

import model.aventurier.Aventurier;

import java.util.List;

public class Carte {
    private Dimensions dimensions;
    private TypeCase[][] plan;

    public Carte(Dimensions dimensions, List<Aventurier> aventuriers) {
        this.dimensions = dimensions;
        int nbCasesLargeur = dimensions.getLargeur().getNbCases();
        int nbCasesHauteur = dimensions.getHauteur().getNbCases();
        plan = new TypeCase[nbCasesLargeur][nbCasesHauteur];
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < nbCasesLargeur; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < nbCasesHauteur; indexAxeVerticale++) {
                plan[indexAxeHorizontale][indexAxeVerticale] = TypeCase.PLAINE;
            }
        }
        placer(aventuriers);
    }

    private void placer(List<Aventurier> aventuriers) {
        aventuriers.forEach(aventurier ->
        {
            int positionDepartVerticale = aventurier.getPositionDepart().getAxeVerticale().getNumCase();
            int positionDepartHorizontale = aventurier.getPositionDepart().getAxeHorizontale().getNumCase();
            plan[positionDepartHorizontale][positionDepartVerticale] = TypeCase.AVENTURIER;
        });
    }

    public Largeur getLargeur() {
        return dimensions.getLargeur();
    }

    public Hauteur getHauteur() {
        return dimensions.getHauteur();
    }

    public TypeCase getCase(Case aCase) {
        return plan[aCase.getAxe().getAxeHorizontale().getNumCase()][aCase.getAxe().getAxeVerticale().getNumCase()];
    }
}
