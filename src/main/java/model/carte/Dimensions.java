package model.carte;

public class Dimensions {
    private int largeur;
    private int hauteur;

    public Dimensions(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
}
