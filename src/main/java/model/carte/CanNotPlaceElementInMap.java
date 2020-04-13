package model.carte;

import static java.lang.String.format;

public class CanNotPlaceElementInMap extends RuntimeException {
    public CanNotPlaceElementInMap(
            final TypeAxe eltToPlace, final Axe axe, final TypeAxe occupyingElt) {
        super(format("%s could not be placed on axe(%d,%d) because a %s is already there",
                eltToPlace, axe.getAxeHorizontal(), axe.getAxeVertical(), occupyingElt));
    }

    public CanNotPlaceElementInMap(final Axe axe) {
        super(format("Element could not be placed on axe(%d,%d) because it's out of Carte",
                axe.getAxeHorizontal(), axe.getAxeHorizontal()));
    }
}
