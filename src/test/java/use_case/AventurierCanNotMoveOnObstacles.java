package use_case;

import model.carte.*;
import model.element.Aventurier;
import model.element.Montagne;
import model.element.Orientation;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanNotMoveOnObstacles {

    public static final int NB_CASES_LARGEUR_CARTE = 3;
    public static final int NB_CASES_HAUTEUR_CARTE = 4;

    @Test
    void montagne() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, Orientation.NORD);
        Element montagne = new Montagne(new Axe(1, 0));
        Carte carte = new Carte(dimensions, asList(laura, montagne));

        carte.avancer(laura);

        assertThat(carte.getAxe(1, 1)).isEqualTo(TypeAxe.AVENTURIER);
        assertThat(carte.getAxe(1, 0)).isEqualTo(TypeAxe.MONTAGNE);
    }
}
