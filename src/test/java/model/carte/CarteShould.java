package model.carte;

import model.element.Aventurier;
import model.element.Montagne;
import model.element.Tresor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static common.TestUtils.NB_CASES_HAUTEUR_CARTE;
import static common.TestUtils.NB_CASES_LARGEUR_CARTE;
import static model.carte.TypeAxe.*;
import static model.element.Orientation.SUD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CarteShould {

    @Test
    void beCreated() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);

        Carte carte = new Carte(dimensions, emptyList());

        assertThat(carte.getLargeur()).isEqualTo(NB_CASES_LARGEUR_CARTE);
        assertThat(carte.getHauteur()).isEqualTo(NB_CASES_HAUTEUR_CARTE);
        assertThatAllCasesArePlaine(carte);
    }

    @Test
    void beCreatedWithAllElementsType() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);
        List<Element> elementsToPlaceOnCarte = getAllTypeElements();

        Carte carte = new Carte(dimensions, elementsToPlaceOnCarte);

        assertThatAllElementsAreWellPlacedOnCarte(carte);
    }

    @Test
    void notBeCreatedWhenAxesAreInConflict() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);
        Element montagne = new Montagne(new Axe(0, 0));
        Element laura = new Aventurier("Laura", new Axe(0, 0), SUD);
        Element tresor = new Tresor(new Axe(0, 0));

        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(laura, montagne)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(montagne, laura)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(laura, laura)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(montagne, montagne)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, asList(tresor, laura)));
    }

    @Test
    void notBeCreatedWhenAnElementIsPlacedOutOfCarte() {
        Dimensions dimensions = new Dimensions(NB_CASES_LARGEUR_CARTE, NB_CASES_HAUTEUR_CARTE);
        Element montagne = new Montagne(new Axe(NB_CASES_LARGEUR_CARTE, 0));
        Element laura = new Aventurier("Laura", new Axe(0, NB_CASES_HAUTEUR_CARTE), SUD);
        Element tresor = new Tresor(new Axe(-1, 0));
        Element tom = new Aventurier("Tom", new Axe(0, -1), SUD);

        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, singletonList(laura)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, singletonList(montagne)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, singletonList(tresor)));
        assertThatExceptionOfType(CanNotPlaceElementInMap.class)
                .isThrownBy(() -> new Carte(dimensions, singletonList(tom)));
    }

    private void assertThatAllCasesArePlaine(Carte carte) {
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < NB_CASES_LARGEUR_CARTE; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < NB_CASES_HAUTEUR_CARTE; indexAxeVerticale++) {
                TypeAxe typeAxe = carte.getAxe(indexAxeHorizontale, indexAxeVerticale);
                assertThat(typeAxe).isEqualTo(PLAINE);
            }
        }
    }

    private List<Element> getAllTypeElements() {
        List<Element> elements = new ArrayList<>();
        elements.add(new Montagne(new Axe(0, 0)));
        elements.add(new Montagne(new Axe(1, 2)));
        elements.add(new Aventurier("Laura", new Axe(1, 1), SUD));
        elements.add(new Aventurier("Tom", new Axe(2, 1), SUD));
        elements.add(new Tresor(new Axe(2, 2)));
        elements.add(new Tresor(new Axe(1, 3)));
        return elements;
    }

    private void assertThatAllElementsAreWellPlacedOnCarte(Carte carte) {
        assertThat(carte.getAxe(0, 0)).isEqualTo(MONTAGNE);
        assertThat(carte.getAxe(1, 2)).isEqualTo(MONTAGNE);
        assertThat(carte.getAxe(1, 1)).isEqualTo(AVENTURIER);
        assertThat(carte.getAxe(2, 1)).isEqualTo(AVENTURIER);
        assertThat(carte.getAxe(2, 2)).isEqualTo(TRESOR);
        assertThat(carte.getAxe(1, 3)).isEqualTo(TRESOR);
    }
}
