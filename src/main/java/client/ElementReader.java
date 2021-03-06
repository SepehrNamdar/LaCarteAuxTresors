package client;

import common.dto.DimensionDTO;
import client.reader.AventurierReader;
import client.reader.MontagneReader;
import client.reader.TresorReader;
import common.dto.ElementDTO;

import java.util.ArrayList;
import java.util.List;

import static client.FileHelper.*;
import static java.lang.Integer.parseInt;

public abstract class ElementReader {
    private static final DimensionDTO dimensions = new DimensionDTO();
    private static final List<ElementDTO> elements = new ArrayList<>();

    public abstract ElementDTO read(final String[] lineArgs);

    public static void processElement(final String[] lineArgs) {
        String eltType = lineArgs[FIRST];
        if (isMontagne(eltType)) {
            ElementReader montagneReader = new MontagneReader();
            elements.add(montagneReader.read(lineArgs));
        } else if (isTresor(eltType)) {
            ElementReader tresorReader = new TresorReader();
            elements.add(tresorReader.read(lineArgs));
        } else if (isAventurier(eltType)) {
            ElementReader aventurierReader = new AventurierReader();
            elements.add(aventurierReader.read(lineArgs));
        }
    }

    private static boolean isAventurier(final String lineArg) {
        return AVENTURIER.equals(lineArg);
    }

    private static boolean isTresor(final String lineArg) {
        return TRESOR.equals(lineArg);
    }

    private static boolean isMontagne(final String lineArg) {
        return MONTAGNE.equals(lineArg);
    }

    public static void processDimensions(final String[] lineArgs) {
        int largeur = getLargeurCarte(lineArgs);
        int hauteur = getHauteurCarte(lineArgs);
        dimensions.setLargeur(largeur);
        dimensions.setHauteur(hauteur);
    }

    private static int getHauteurCarte(final String[] lineArgs) {
        String hauteurCarte = lineArgs[THIRD];
        return parseInt(hauteurCarte);
    }

    private static int getLargeurCarte(final String[] lineArgs) {
        String largeurCarte = lineArgs[SECOND];
        return parseInt(largeurCarte);
    }

    public static DimensionDTO getDimensions() {
        return dimensions;
    }

    public static List<ElementDTO> getElements() {
        return elements;
    }
}
