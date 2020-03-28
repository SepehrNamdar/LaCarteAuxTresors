package model.carte;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarteShould {

    public static final int NB_CASES_LARGEUR_CARTE = 3;
    public static final int NB_CASES_HAUTEUR_CARTE = 4;

    @Test
    void beCreated() {
        Largeur largeur = new Largeur(NB_CASES_LARGEUR_CARTE);
        Hauteur hauteur = new Hauteur(NB_CASES_HAUTEUR_CARTE);
        Dimensions dimensions = new Dimensions(largeur, hauteur);

        Carte carte = new Carte(dimensions);

        assertThat(carte.getLargeur()).isEqualTo(largeur);
        assertThat(carte.getHauteur()).isEqualTo(hauteur);
        assertThatAllCasesArePlaine(carte);
    }

    private void assertThatAllCasesArePlaine(Carte carte) {
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < NB_CASES_LARGEUR_CARTE; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < NB_CASES_HAUTEUR_CARTE; indexAxeVerticale++) {
                Axe axe = new Axe(new AxeHorizontale(indexAxeHorizontale), new AxeVerticale(indexAxeVerticale));
                String aCase = carte.getCase(new Case(axe));
                assertThat(aCase).isEqualTo(".");
            }
        }
    }
}
