package model.carte;

import model.aventurier.Aventurier;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
        Dimensions dimensions = getCarteDimensions();
        Element laura = getAventurier("Laura", 0, 0);
        Element tom = getAventurier("Tom", 1, 2);

        Carte carte = new Carte(dimensions, asList(laura, tom));

        assertThat(carte.getAxe(0, 0)).isEqualTo(TypeAxe.AVENTURIER);
        assertThat(carte.getAxe(1, 2)).isEqualTo(TypeAxe.AVENTURIER);
    }

    @Test
    void beCreatedWithObstacles() {
        Dimensions dimensions = getCarteDimensions();
        Element montagne_1 = getMontagne(0, 0);
        Element montagne_2 = getMontagne(1, 2);
        Carte carte = new Carte(dimensions, asList(montagne_1, montagne_2));

        assertThat(carte.getAxe(0, 0)).isEqualTo(TypeAxe.MONTAGNE);
        assertThat(carte.getAxe(1, 2)).isEqualTo(TypeAxe.MONTAGNE);
    }

    @Test
    void beCreatedWithAventuriersAndMontagnes() {
        Dimensions dimensions = getCarteDimensions();
        Element montagne_1 = getMontagne(0, 0);
        Element montagne_2 = getMontagne(1, 2);
        Element laura = getAventurier("Laura", 1, 1);
        Element tom = getAventurier("Tom", 2, 1);

        Carte carte = new Carte(dimensions, asList(laura, tom, montagne_1, montagne_2));

        assertThat(carte.getAxe(0, 0)).isEqualTo(TypeAxe.MONTAGNE);
        assertThat(carte.getAxe(1, 2)).isEqualTo(TypeAxe.MONTAGNE);
        assertThat(carte.getAxe(1, 1)).isEqualTo(TypeAxe.AVENTURIER);
        assertThat(carte.getAxe(2, 1)).isEqualTo(TypeAxe.AVENTURIER);
    }

    @Test
    void notBeCreatedWhenAxesAreInConflict() {
        Dimensions dimensions = getCarteDimensions();
        Element montagne = getMontagne(0, 0);
        Element laura = getAventurier("Laura", 0, 0);

        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(laura, montagne)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(montagne, laura)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(laura, laura)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(montagne, montagne)));
    }

    private Element getAventurier(String nom, int axeHorizontale, int axeVerticale) {
        return new Aventurier(nom, new Axe(new AxeHorizontale(axeHorizontale), new AxeVerticale(axeVerticale)));
    }

    private Element getMontagne(int axeHorizontale, int axeVerticale) {
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
                TypeAxe typeAxe = carte.getAxe(indexAxeHorizontale, indexAxeVerticale);
                assertThat(typeAxe).isEqualTo(TypeAxe.PLAINE);
            }
        }
    }
}
