package model.carte;

import model.element.Element;

import java.util.ArrayList;
import java.util.List;

import static model.carte.TypeAxe.*;

public class Carte {
    private final Dimensions dimensions;
    private final List<Element> elements;
    private TypeAxe[][] plan;
    private static List<Element> tresors;
    private static List<Element> aventuriers;
    private static List<Element> obstacles;

    public Carte(final Dimensions dimensions, final List<Element> elements) {
        this.dimensions = dimensions;
        this.elements = elements;
        tresors = new ArrayList<>();
        aventuriers = new ArrayList<>();
        obstacles = new ArrayList<>();
        initPlan();
        placer(this.elements);
    }

    private void initPlan() {
        int carteLargeur = dimensions.getLargeur();
        int carteHauteur = dimensions.getHauteur();
        plan = new TypeAxe[carteLargeur][carteHauteur];
        for (int indexAxeVertical = 0; indexAxeVertical < carteHauteur; indexAxeVertical++) {
            for (int indexAxeHorizontal = 0; indexAxeHorizontal < carteLargeur; indexAxeHorizontal++) {
                plan[indexAxeHorizontal][indexAxeVertical] = PLAINE;
            }
        }
    }

    private void placer(final List<Element> elements) {
        elements.forEach(element ->
        {
            checkElementAxe(element.getAxe());
            int axeHorizontal = element.getAxe().getAxeHorizontal();
            int axeVertical = element.getAxe().getAxeVertical();
            TypeAxe occupyingAxe = plan[axeHorizontal][axeVertical];
            TypeAxe eltToPlaceType = element.getType();
            if (occupyingAxe == PLAINE) {
                plan[axeHorizontal][axeVertical] = eltToPlaceType;
            } else {
                throw new CanNotPlaceElementInMap(eltToPlaceType, element.getAxe(), eltToPlaceType);
            }
            if (TRESOR.equals(eltToPlaceType)) {
                tresors.add(element);
            } else if (AVENTURIER.equals(eltToPlaceType)) {
                aventuriers.add(element);
            } else if (MONTAGNE.equals(eltToPlaceType)) {
                obstacles.add(element);
            }
        });
    }

    private void checkElementAxe(final Axe axe) {
        if (isOutOfCarte(axe)) {
            throw new CanNotPlaceElementInMap(axe);
        }
    }

    public static List<Element> getTresors() {
        return tresors;
    }

    public static List<Element> getAventuriers() {
        return aventuriers;
    }

    public static List<Element> getObstacles() {
        return obstacles;
    }

    public TypeAxe getAxe(final int axeHorizontal, final int axeVertical) {
        return plan[axeHorizontal][axeVertical];
    }

    public int getLargeur() {
        return dimensions.getLargeur();
    }

    public int getHauteur() {
        return dimensions.getHauteur();
    }

    private boolean isOutOfCarte(final Axe axe) {
        return axe.getAxeVertical() >= dimensions.getHauteur() ||
                axe.getAxeHorizontal() >= dimensions.getLargeur() ||
                axe.getAxeHorizontal() < 0 || axe.getAxeVertical() < 0;
    }

    public List<Element> getElements() {
        return elements;
    }
}
