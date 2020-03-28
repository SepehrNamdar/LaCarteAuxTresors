package model.carte;

public class Carte {
    private Dimensions dimensions;

    public Carte(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Largeur getLargeur() {
        return dimensions.getLargeur();
    }

    public Hauteur getHauteur() {
        return dimensions.getHauteur();
    }
}
