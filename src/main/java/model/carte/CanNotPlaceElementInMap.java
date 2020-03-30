package model.carte;

import static java.lang.String.format;

public class CanNotPlaceElementInMap extends RuntimeException {
    public CanNotPlaceElementInMap(
            TypeAxe eltToPlace, Axe axe, TypeAxe occupyingElt) {
        super(format("%s could not be placed on axe(%d,%d) because a %s is already there",
                eltToPlace, axe.getAxeHorizontale(), axe.getAxeVerticale(), occupyingElt));
    }

    public CanNotPlaceElementInMap(Axe axe) {
        super(format("Element could not be placed on axe(%d,%d) because it's out of Carte",
                axe.getAxeHorizontale(), axe.getAxeHorizontale()));
    }
}
