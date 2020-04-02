package use_case;

import model.carte.*;
import model.element.Aventurier;
import model.element.Tresor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static common.TestUtils.LARGEUR_CARTE;
import static common.TestUtils.HAUTEUR_CARTE;
import static model.carte.TypeAxe.*;
import static model.element.Orientation.EST;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanGainTresor {

    @Test
    void crossingTresorAxe() {
        Dimensions dimensions = new Dimensions(HAUTEUR_CARTE, LARGEUR_CARTE);
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, EST);
        Tresor tresor = new Tresor(new Axe(2, 1), 2);
        Carte carte = new Carte(dimensions, Arrays.asList(laura, tresor));
        tracer(carte);

        carte.avancer(laura);
        tracer(carte);
        carte.avancer(laura);
        tracer(carte);
        assertThat(carte.getAxe(1, 1)).isEqualTo(PLAINE);
        assertThat(carte.getAxe(2, 1)).isEqualTo(TRESOR);
        carte.turnRight(laura);
        tracer(carte);
        carte.avancer(laura);
        tracer(carte);
        assertThat(carte.getAxe(2, 1)).isEqualTo(TRESOR);
        assertThat(carte.getAxe(2, 0)).isEqualTo(AVENTURIER);
        assertThat(tresor.getNbTresor()).isEqualTo(1);
        assertThat(laura.getNbTresor()).isEqualTo(1);
    }

    private void tracer(Carte carte) {
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < HAUTEUR_CARTE; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < LARGEUR_CARTE; indexAxeVerticale++) {
                TypeAxe typeAxe = carte.getAxe(indexAxeHorizontale, indexAxeVerticale);
                System.out.print(typeAxe + " ");
            }
            System.out.println();
        }
        System.out.println("=========================");
    }
}
