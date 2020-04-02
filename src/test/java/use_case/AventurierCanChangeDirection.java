package use_case;

import model.carte.Axe;
import model.carte.Carte;
import model.carte.Dimensions;
import model.element.Aventurier;
import model.element.Orientation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static common.TestUtils.NB_CASES_HAUTEUR_CARTE;
import static common.TestUtils.NB_CASES_LARGEUR_CARTE;
import static java.util.Collections.singletonList;
import static model.element.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanChangeDirection {

    @Test
    void toLeft() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, NORD);
        Carte carte = new Carte(dimensions, singletonList(laura));

        carte.turnLeft(laura);
        assertThat(laura.getCurrentOrientation()).isEqualTo(OUEST);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        carte.turnLeft(laura);
        assertThat(laura.getCurrentOrientation()).isEqualTo(SUD);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        carte.turnLeft(laura);
        assertThat(laura.getCurrentOrientation()).isEqualTo(EST);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        carte.turnLeft(laura);
        assertThat(laura.getCurrentOrientation()).isEqualTo(NORD);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);
    }
}
