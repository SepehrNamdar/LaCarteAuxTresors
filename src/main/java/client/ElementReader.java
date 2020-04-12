package client;

import application.DimensionDTO;
import common.ElementDTO;

import java.util.ArrayList;
import java.util.List;

import static client.FileHelper.*;
import static java.lang.Integer.parseInt;

public abstract class ElementReader {

    public static List<ElementDTO> processElement(String[] lineArgs) {
        final List<ElementDTO> elements = new ArrayList<>();
        String firstArg = lineArgs[FIRST];
        if (isMontagne(firstArg)) {
            elements.add(getMontagne(lineArgs));
        } else if (isTresor(firstArg)) {
            elements.add(getTresor(lineArgs));
        } else if (isAventurier(firstArg)) {
            elements.add(getAventurier(lineArgs));
        }
        return elements;
    }

    private static boolean isAventurier(String lineArg) {
        return AVENTURIER.equals(lineArg);
    }

    private static boolean isTresor(String lineArg) {
        return TRESOR.equals(lineArg);
    }

    private static boolean isMontagne(String lineArg) {
        return MONTAGNE.equals(lineArg);
    }

    private static ElementDTO getAventurier(String[] lineArgs) {
        ElementDTO aventurier = new ElementDTO();
        aventurier.setType(AVENTURIER);
        aventurier.setName(lineArgs[SECOND]);
        aventurier.setAxeHorizontal(parseInt(lineArgs[THIRD]));
        aventurier.setAxeVertical(parseInt(lineArgs[FOURTH]));
        aventurier.setOrientation(lineArgs[FIFTH]);
        aventurier.setMouvements(lineArgs[SIXTH]);
        return aventurier;
    }

    private static ElementDTO getTresor(String[] lineArgs) {
        ElementDTO tresor = new ElementDTO();
        tresor.setType(TRESOR);
        tresor.setAxeHorizontal(parseInt(lineArgs[SECOND]));
        tresor.setAxeVertical(parseInt(lineArgs[THIRD]));
        tresor.setNbTresor(parseInt(lineArgs[FOURTH]));
        return tresor;
    }

    private static ElementDTO getMontagne(String[] lineArgs) {
        ElementDTO montagne = new ElementDTO();
        montagne.setType(MONTAGNE);
        montagne.setAxeHorizontal(parseInt(lineArgs[SECOND]));
        montagne.setAxeVertical(parseInt(lineArgs[THIRD]));
        return montagne;
    }

    public static DimensionDTO initDimensions(String[] lineArgs) {
        int largeur = getLargeurCarte(lineArgs);
        int hauteur = getHauteurCarte(lineArgs);
        DimensionDTO dimensions = new DimensionDTO();
        dimensions.setLargeur(largeur);
        dimensions.setHauteur(hauteur);
        return dimensions;
    }

    private static int getHauteurCarte(String[] lineArgs) {
        String hauteurCarte = lineArgs[THIRD];
        return parseInt(hauteurCarte);
    }

    private static int getLargeurCarte(String[] lineArgs) {
        String largeurCarte = lineArgs[SECOND];
        return parseInt(largeurCarte);
    }

}
