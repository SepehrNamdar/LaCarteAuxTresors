package model.carte;

public class Dimensions {
    private Largeur largeur;
    private Hauteur hauteur;

    public Dimensions(Largeur largeur, Hauteur hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public Largeur getLargeur() {
        return largeur;
    }

    public Hauteur getHauteur() {
        return hauteur;
    }
}
