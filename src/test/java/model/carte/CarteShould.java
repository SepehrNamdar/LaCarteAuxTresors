package model.carte;

import model.aventurier.Aventurier;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

        Carte carte = new Carte(dimensions, Collections.emptyList(), Collections.emptyList());

        assertThat(carte.getLargeur()).isEqualTo(largeur);
        assertThat(carte.getHauteur()).isEqualTo(hauteur);
        assertThatAllCasesArePlaine(carte);
    }

    @Test
    void beCreatedWithAventuriers() {
        Dimensions dimensions = getCarteDimensions();
        Aventurier laura = getAventurier("Laura", 0, 0);
        Aventurier tom = getAventurier("Tom", 1, 2);

        Carte carte = new Carte(dimensions, asList(laura, tom), Collections.emptyList());

        assertThat(carte.getAxe(0, 0)).isEqualTo(TypeAxe.AVENTURIER);
        assertThat(carte.getAxe(1, 2)).isEqualTo(TypeAxe.AVENTURIER);
    }

    @Test
    void beCreatedWithObstacles() {
        Dimensions dimensions = getCarteDimensions();
        Obstacle montagne_1 = getMontagne(0, 0);
        Obstacle montagne_2 = getMontagne(1, 2);
        Carte carte = new Carte(dimensions, Collections.emptyList(), Arrays.asList(montagne_1, montagne_2));

        assertThat(carte.getAxe(0, 0)).isEqualTo(TypeAxe.OBSTACLE);
        assertThat(carte.getAxe(1, 2)).isEqualTo(TypeAxe.OBSTACLE);
    }

    @Test
    void beCreatedWithAventuriersAndMontagnes() {
        Dimensions dimensions = getCarteDimensions();
        Obstacle montagne_1 = getMontagne(0, 0);
        Obstacle montagne_2 = getMontagne(1, 2);
        Aventurier laura = getAventurier("Laura", 1, 1);
        Aventurier tom = getAventurier("Tom", 2, 1);

        Carte carte = new Carte(dimensions, asList(laura, tom), Arrays.asList(montagne_1, montagne_2));

        assertThat(carte.getAxe(0, 0)).isEqualTo(TypeAxe.OBSTACLE);
        assertThat(carte.getAxe(1, 2)).isEqualTo(TypeAxe.OBSTACLE);
        assertThat(carte.getAxe(1, 1)).isEqualTo(TypeAxe.AVENTURIER);
        assertThat(carte.getAxe(2, 1)).isEqualTo(TypeAxe.AVENTURIER);
    }

    private Aventurier getAventurier(String nom, int axeHorizontale, int axeVerticale) {
        return new Aventurier(nom, new Axe(new AxeHorizontale(axeHorizontale), new AxeVerticale(axeVerticale)));
    }

    private Montagne getMontagne(int axeHorizontale, int axeVerticale) {
        return new Montagne(new Axe(new AxeHorizontale(axeHorizontale), new AxeVerticale(axeVerticale)));
    }

    private Dimensions getCarteDimensions() {
        Largeur largeur = new Largeur(NB_CASES_LARGEUR_CARTE);
        Hauteur hauteur = new Hauteur(NB_CASES_HAUTEUR_CARTE);
        return new Dimensions(largeur, hauteur);
    }

    private void assertThatAllCasesArePlaine(Carte carte) {
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < NB_CASES_LARGEUR_CARTE; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < NB_CASES_HAUTEUR_CARTE; indexAxeVerticale++) {
                TypeAxe aCase = carte.getAxe(indexAxeHorizontale, indexAxeVerticale);
                assertThat(aCase).isEqualTo(TypeAxe.PLAINE);
            }
        }
    }
}
