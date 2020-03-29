package use_case;

import model.aventurier.Aventurier;
import model.carte.*;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;

public class AventurierPeutTraverserLaCarte {

    public static final int NB_CASES_LARGEUR_CARTE = 3;
    public static final int NB_CASES_HAUTEUR_CARTE = 4;

    @Test
    void pleine() {
        Largeur largeur = new Largeur(NB_CASES_LARGEUR_CARTE);
        Hauteur hauteur = new Hauteur(NB_CASES_HAUTEUR_CARTE);
        Dimensions dimensions = new Dimensions(largeur, hauteur);
        Axe axeDepart = new Axe(new AxeHorizontale(2), new AxeVerticale(1));
        Aventurier laura = new Aventurier("Laura", axeDepart);
        Carte carte = new Carte(dimensions, singletonList(laura));

    }
}