package model.carte;

import model.element.Aventurier;
import model.element.Montagne;
import model.element.Tresor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CarteShould {

    public static final int LARGEUR_CARTE = 3;
    public static final int HAUTEUR_CARTE = 4;

    @Test
    void beCreated() {
        Dimensions dimensions = new Dimensions(LARGEUR_CARTE, HAUTEUR_CARTE);

        Carte carte = new Carte(dimensions, Collections.emptyList());

        assertThat(carte.getLargeur()).isEqualTo(LARGEUR_CARTE);
        assertThat(carte.getHauteur()).isEqualTo(HAUTEUR_CARTE);
        assertThatAllCasesArePlaine(carte);
    }

    @Test
    void beCreatedWithAllElementsType() {
        Dimensions dimensions = new Dimensions(LARGEUR_CARTE, HAUTEUR_CARTE);
        List<Element> elementsToPlaceOnCarte = getAllTypeElements();

        Carte carte = new Carte(dimensions, elementsToPlaceOnCarte);

        assertThatAllElementsAreWellPlacedOnCarte(carte);
    }

    @Test
    void notBeCreatedWhenAxesAreInConflict() {
        Dimensions dimensions = new Dimensions(LARGEUR_CARTE, HAUTEUR_CARTE);
        Element montagne = new Montagne(new Axe(0, 0));
        Element laura = new Aventurier("Laura", new Axe(0, 0));
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

    private void assertThatAllCasesArePlaine(Carte carte) {
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < LARGEUR_CARTE; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < HAUTEUR_CARTE; indexAxeVerticale++) {
                TypeAxe typeAxe = carte.getAxe(indexAxeHorizontale, indexAxeVerticale);
                assertThat(typeAxe).isEqualTo(TypeAxe.PLAINE);
            }
        }
    }

    private List<Element> getAllTypeElements() {
        List<Element> elements = new ArrayList<>();
        elements.add(new Montagne(new Axe(0, 0)));
        elements.add(new Montagne(new Axe(1, 2)));
        elements.add(new Aventurier("Laura", new Axe(1, 1)));
        elements.add(new Aventurier("Tom", new Axe(2, 1)));
        elements.add(new Tresor(new Axe(2, 2)));
        elements.add(new Tresor(new Axe(1, 3)));
        return elements;
    }

    private void assertThatAllElementsAreWellPlacedOnCarte(Carte carte) {
        assertThat(carte.getAxe(0, 0)).isEqualTo(TypeAxe.MONTAGNE);
        assertThat(carte.getAxe(1, 2)).isEqualTo(TypeAxe.MONTAGNE);
        assertThat(carte.getAxe(1, 1)).isEqualTo(TypeAxe.AVENTURIER);
        assertThat(carte.getAxe(2, 1)).isEqualTo(TypeAxe.AVENTURIER);
        assertThat(carte.getAxe(2, 2)).isEqualTo(TypeAxe.TRESOR);
        assertThat(carte.getAxe(1, 3)).isEqualTo(TypeAxe.TRESOR);
    }
}
