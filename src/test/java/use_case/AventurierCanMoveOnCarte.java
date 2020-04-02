package use_case;

import model.element.Aventurier;
import model.carte.*;
import model.element.Orientation;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanMoveOnCarte {

    public static final int NB_CASES_LARGEUR_CARTE = 3;
    public static final int NB_CASES_HAUTEUR_CARTE = 4;

    @Test
    void plaine() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);
        Axe axeDepart = new Axe(2, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, Orientation.SUD);
        Carte carte = new Carte(dimensions, singletonList(laura));

        carte.avancer(laura);

        assertThat(carte.getAxe(2, 1)).isEqualTo(TypeAxe.PLAINE);
        assertThat(carte.getAxe(2, 2)).isEqualTo(TypeAxe.AVENTURIER);

        carte.avancer(laura);

        assertThat(carte.getAxe(2, 2)).isEqualTo(TypeAxe.PLAINE);
        assertThat(carte.getAxe(2, 3)).isEqualTo(TypeAxe.AVENTURIER);

        carte.avancer(laura);

        assertThat(carte.getAxe(2, 3)).isEqualTo(TypeAxe.AVENTURIER);
    }
}