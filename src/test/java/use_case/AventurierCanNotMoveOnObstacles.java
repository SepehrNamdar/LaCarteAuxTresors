package use_case;

import model.carte.*;
import model.element.Aventurier;
import model.element.Element;
import model.element.Montagne;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static common.TestUtils.HAUTEUR_CARTE;
import static common.TestUtils.LARGEUR_CARTE;
import static model.element.Orientation.NORD;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanNotMoveOnObstacles {

    @Test
    void montagne() {
        Dimensions dimensions = new Dimensions(LARGEUR_CARTE, HAUTEUR_CARTE);
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, NORD, "A");
        Axe axeMontagne = new Axe(1, 0);
        Element montagne = new Montagne(axeMontagne);
        new Carte(dimensions, asList(laura, montagne));

        laura.avancer();

        assertThat(laura.getAxe()).isEqualTo(axeDepart);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);
    }
}
