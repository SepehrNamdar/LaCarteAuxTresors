package model.carte;

public class Carte {
    private Dimensions dimensions;
    private Plan plan;

    public Carte(Dimensions dimensions) {
        this.dimensions = dimensions;
        this.plan = new Plan(dimensions.getLargeur().getNbCases(), dimensions.getHauteur().getNbCases());
    }

    public Largeur getLargeur() {
        return dimensions.getLargeur();
    }

    public Hauteur getHauteur() {
        return dimensions.getHauteur();
    }

    public String getCase(Case aCase) {
        return plan.getAxe(aCase.getAxe());
    }
}
