package model.carte;

import static java.lang.String.format;

public class CanNotPlaceElementInMap extends RuntimeException {
    public CanNotPlaceElementInMap(
            TypeAxe eltToPlace, int eltToPlaceAxeHorizontale, int eltToPlaceAxeVerticale, TypeAxe occupyingElt) {
        super(format("%s could not be placed on axe(%d,%d) because a %s is already there",
                eltToPlace, eltToPlaceAxeVerticale, eltToPlaceAxeHorizontale, occupyingElt));
    }
}
