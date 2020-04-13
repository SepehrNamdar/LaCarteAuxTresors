package use_case;

import model.carte.*;
import model.element.Aventurier;
import model.element.Element;
import model.element.Montagne;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static common.TestUtils.HAUTEUR_CARTE;
import static common.TestUtils.LARGEUR_CARTE;
import static model.element.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanNotMove {

    @Test
    void onMontagne() {
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

    @Test
    void outOfCarte() {
        Dimensions dimensions = new Dimensions(2, 2);
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, EST, "ADADAAADAADA");
        Axe axeMontagne = new Axe(1, 0);
        Element montagne = new Montagne(axeMontagne);
        new Carte(dimensions, asList(laura, montagne));

        assertThat(laura.getAxe()).isEqualTo(axeDepart);
        assertThat(laura.getCurrentOrientation()).isEqualTo(EST);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.avancer();

        assertThat(laura.getAxe()).isEqualTo(axeDepart);
        assertThat(laura.getCurrentOrientation()).isEqualTo(EST);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.turnRight();

        assertThat(laura.getAxe()).isEqualTo(axeDepart);
        assertThat(laura.getCurrentOrientation()).isEqualTo(SUD);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.avancer();

        assertThat(laura.getAxe()).isEqualTo(axeDepart);
        assertThat(laura.getCurrentOrientation()).isEqualTo(SUD);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.turnRight();

        assertThat(laura.getAxe()).isEqualTo(axeDepart);
        assertThat(laura.getCurrentOrientation()).isEqualTo(OUEST);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.avancer();

        assertThat(laura.getAxe()).isEqualTo(new Axe(0, 1));
        assertThat(laura.getCurrentOrientation()).isEqualTo(OUEST);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.avancer();

        assertThat(laura.getAxe()).isEqualTo(new Axe(0, 1));
        assertThat(laura.getCurrentOrientation()).isEqualTo(OUEST);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.turnRight();

        assertThat(laura.getAxe()).isEqualTo(new Axe(0, 1));
        assertThat(laura.getCurrentOrientation()).isEqualTo(NORD);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.avancer();

        assertThat(laura.getAxe()).isEqualTo(new Axe(0, 0));
        assertThat(laura.getCurrentOrientation()).isEqualTo(NORD);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.avancer();

        assertThat(laura.getAxe()).isEqualTo(new Axe(0, 0));
        assertThat(laura.getCurrentOrientation()).isEqualTo(NORD);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.turnRight();

        assertThat(laura.getAxe()).isEqualTo(new Axe(0, 0));
        assertThat(laura.getCurrentOrientation()).isEqualTo(EST);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);

        laura.avancer();

        assertThat(laura.getAxe()).isEqualTo(new Axe(0, 0));
        assertThat(laura.getCurrentOrientation()).isEqualTo(EST);
        assertThat(laura.getNbTresor()).isEqualTo(0);
        assertThat(montagne.getAxe()).isEqualTo(axeMontagne);
    }
}
