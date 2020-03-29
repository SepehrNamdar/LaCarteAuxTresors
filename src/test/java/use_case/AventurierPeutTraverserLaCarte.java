package use_case;

import model.element.Aventurier;
import model.carte.*;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;

public class AventurierPeutTraverserLaCarte {

    public static final int NB_CASES_LARGEUR_CARTE = 3;
    public static final int NB_CASES_HAUTEUR_CARTE = 4;

    @Test
    void pleine() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);
        Axe axeDepart = new Axe(2, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart);
        Carte carte = new Carte(dimensions, singletonList(laura));

    }
}