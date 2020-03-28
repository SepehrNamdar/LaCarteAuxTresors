package use_case;

import model.aventurier.Aventurier;
import model.carte.Carte;
import model.carte.Dimensions;
import model.carte.Hauteur;
import model.carte.Largeur;
import org.junit.jupiter.api.Test;

public class AventurierPeutTraverserLaCarte {

    public static final int NB_CASES_LARGEUR_CARTE = 3;
    public static final int NB_CASES_HAUTEUR_CARTE = 4;

    @Test
    void pleine() {
        Largeur largeur = new Largeur(NB_CASES_LARGEUR_CARTE);
        Hauteur hauteur = new Hauteur(NB_CASES_HAUTEUR_CARTE);
        Dimensions dimensions = new Dimensions(largeur, hauteur);

        Carte carte = new Carte(dimensions);
        Aventurier laura = new Aventurier("Laura");

    }
}