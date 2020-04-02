package use_case;

import model.carte.*;
import model.element.Aventurier;
import model.element.Tresor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static common.TestUtils.NB_CASES_HAUTEUR_CARTE;
import static common.TestUtils.NB_CASES_LARGEUR_CARTE;
import static model.carte.TypeAxe.*;
import static model.element.Orientation.EST;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanGainTresor {

    @Test
    void crossingTresorAxe() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, EST);
        Tresor tresor = new Tresor(new Axe(2, 1), 2);
        Carte carte = new Carte(dimensions, Arrays.asList(laura, tresor));

        carte.avancer(laura);
        carte.avancer(laura);
        assertThat(carte.getAxe(1, 1)).isEqualTo(PLAINE);
        assertThat(carte.getAxe(2, 1)).isEqualTo(TRESOR);
        carte.turnRight(laura);
        carte.avancer(laura);
        assertThat(carte.getAxe(2, 1)).isEqualTo(TRESOR);
        assertThat(carte.getAxe(2, 0)).isEqualTo(AVENTURIER);
        assertThat(tresor.getNbTresor()).isEqualTo(1);
        assertThat(laura.getNbTresor()).isEqualTo(1);
    }
}
