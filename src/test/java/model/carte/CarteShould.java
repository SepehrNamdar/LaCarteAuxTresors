package model.carte;

import model.aventurier.Aventurier;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CarteShould {

    public static final int NB_CASES_LARGEUR_CARTE = 3;
    public static final int NB_CASES_HAUTEUR_CARTE = 4;

    @Test
    void beCreated() {
        Largeur largeur = new Largeur(NB_CASES_LARGEUR_CARTE);
        Hauteur hauteur = new Hauteur(NB_CASES_HAUTEUR_CARTE);
        Dimensions dimensions = new Dimensions(largeur, hauteur);

        Carte carte = new Carte(dimensions, Collections.emptyList());

        assertThat(carte.getLargeur()).isEqualTo(largeur);
        assertThat(carte.getHauteur()).isEqualTo(hauteur);
        assertThatAllCasesArePlaine(carte);
    }

    @Test
    void beCreatedWithAventuriers() {
        Largeur largeur = new Largeur(NB_CASES_LARGEUR_CARTE);
        Hauteur hauteur = new Hauteur(NB_CASES_HAUTEUR_CARTE);
        Dimensions dimensions = new Dimensions(largeur, hauteur);
        Axe positionDepartLaura = new Axe(new AxeHorizontale(0), new AxeVerticale(0));
        Aventurier laura = new Aventurier("Laura", positionDepartLaura);
        Axe positionDepartTom = new Axe(new AxeHorizontale(1), new AxeVerticale(2));
        Aventurier tom = new Aventurier("Tom", positionDepartTom);

        Carte carte = new Carte(dimensions, asList(laura, tom));

        assertThat(carte.getCase(new Case(positionDepartLaura))).isEqualTo(TypeCase.AVENTURIER);
        assertThat(carte.getCase(new Case(positionDepartTom))).isEqualTo(TypeCase.AVENTURIER);
    }

    private void assertThatAllCasesArePlaine(Carte carte) {
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < NB_CASES_LARGEUR_CARTE; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < NB_CASES_HAUTEUR_CARTE; indexAxeVerticale++) {
                Axe axe = new Axe(new AxeHorizontale(indexAxeHorizontale), new AxeVerticale(indexAxeVerticale));
                TypeCase aCase = carte.getCase(new Case(axe));
                assertThat(aCase).isEqualTo(TypeCase.PLAINE);
            }
        }
    }
}
