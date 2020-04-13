package use_case;

import model.carte.*;
import model.element.Aventurier;
import model.element.Tresor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static model.element.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;
import static common.TestUtils.HAUTEUR_CARTE;
import static common.TestUtils.LARGEUR_CARTE;

public class AventurierCanGainTresor {

    @Test
    void crossingTresorAxe() {
        Dimensions dimensions = new Dimensions(LARGEUR_CARTE, HAUTEUR_CARTE);
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, EST, "AADA");
        Tresor tresor = new Tresor(new Axe(2, 1), 2);
        new Carte(dimensions, Arrays.asList(laura, tresor));

        assertThat(laura.getAxe().equals(new Axe(1, 1)));
        assertThat(EST.equals(laura.getCurrentOrientation()));
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(tresor.getNbTresor()).isEqualTo(2);

        laura.avancer();

        assertThat(laura.getAxe().equals(new Axe(2, 1)));
        assertThat(EST.equals(laura.getCurrentOrientation()));
        assertThat(laura.getNbTresor()).isEqualTo(1);
        assertThat(tresor.getNbTresor()).isEqualTo(1);

        laura.avancer();

        assertThat(laura.getAxe().equals(new Axe(2, 1)));
        assertThat(EST.equals(laura.getCurrentOrientation()));
        assertThat(laura.getNbTresor()).isEqualTo(1);
        assertThat(tresor.getNbTresor()).isEqualTo(1);

        laura.turnRight();

        assertThat(laura.getAxe().equals(new Axe(2, 1)));
        assertThat(SUD.equals(laura.getCurrentOrientation()));
        assertThat(laura.getNbTresor()).isEqualTo(1);
        assertThat(tresor.getNbTresor()).isEqualTo(1);

        laura.avancer();

        assertThat(laura.getAxe().equals(new Axe(2, 2)));
        assertThat(SUD.equals(laura.getCurrentOrientation()));
        assertThat(laura.getNbTresor()).isEqualTo(1);
        assertThat(tresor.getNbTresor()).isEqualTo(1);
    }
}
