package use_case;

import model.element.Aventurier;
import model.carte.*;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static common.TestUtils.HAUTEUR_CARTE;
import static common.TestUtils.LARGEUR_CARTE;
import static model.carte.TypeAxe.AVENTURIER;
import static model.carte.TypeAxe.PLAINE;
import static model.element.Orientation.SUD;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanMoveOnCarte {

    @Test
    void plaine() {
        Dimensions dimensions = new Dimensions(LARGEUR_CARTE, HAUTEUR_CARTE);
        Axe axeDepart = new Axe(2, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, SUD, "AAA");
        Carte carte = new Carte(dimensions, singletonList(laura));

        carte.avancer(laura);

        assertThat(carte.getAxe(2, 1)).isEqualTo(PLAINE);
        assertThat(carte.getAxe(2, 2)).isEqualTo(AVENTURIER);

        carte.avancer(laura);

        assertThat(carte.getAxe(2, 2)).isEqualTo(PLAINE);
        assertThat(carte.getAxe(2, 3)).isEqualTo(AVENTURIER);

        carte.avancer(laura);

        assertThat(carte.getAxe(2, 3)).isEqualTo(AVENTURIER);
    }
}