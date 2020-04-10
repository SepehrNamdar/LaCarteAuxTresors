package client;

import application.DimensionDTO;
import application.ElementDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class Reader {

    private DimensionDTO dimensions = new DimensionDTO();
    private List<ElementDTO> elementsRequest = new ArrayList<>();

    public void read(String inputFilePath) {
        try (Stream<String> lines = Files.lines(Paths.get(inputFilePath))) {
            initCarteDimensionsAndElements(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCarteDimensionsAndElements(Stream<String> lines) {
        lines.forEach(line -> {
            if (isNotComment(line)) {
                process(line);
            }
        });
    }

    private void process(String line) {
        String[] lineArgs = line.split(Main.SEPARATOR);
        String firstArg = lineArgs[Main.FIRST];
        if (isCarte(firstArg)) {
            initDimensions(lineArgs);
        } else if (isMontagne(firstArg)) {
            elementsRequest.add(getMontagne(lineArgs));
        } else if (isTresor(firstArg)) {
            elementsRequest.add(getTresor(lineArgs));
        } else if (isAventurier(firstArg)) {
            elementsRequest.add(getAventurier(lineArgs));
        }
    }

    private boolean isNotComment(String line) {
        return !line.isEmpty() && !Main.COMMENT.equals(valueOf(line.charAt(Main.FIRST)));
    }

    private boolean isAventurier(String lineArg) {
        return Main.AVENTURIER.equals(lineArg);
    }

    private boolean isTresor(String lineArg) {
        return Main.TRESOR.equals(lineArg);
    }

    private boolean isMontagne(String lineArg) {
        return Main.MONTAGNE.equals(lineArg);
    }

    private boolean isCarte(String lineArg) {
        return Main.CARTE.equals(lineArg);
    }

    private ElementDTO getAventurier(String[] lineArgs) {
        ElementDTO aventurier = new ElementDTO();
        aventurier.setType(Main.AVENTURIER);
        aventurier.setName(lineArgs[Main.SECOND]);
        aventurier.setAxeHorizontal(parseInt(lineArgs[Main.THIRD]));
        aventurier.setAxeVertical(parseInt(lineArgs[Main.FOURTH]));
        aventurier.setOrientation(lineArgs[Main.FIFTH]);
        aventurier.setMouvements(lineArgs[Main.SIXTH]);
        return aventurier;
    }

    private ElementDTO getTresor(String[] lineArgs) {
        ElementDTO tresor = new ElementDTO();
        tresor.setType(Main.TRESOR);
        tresor.setAxeHorizontal(parseInt(lineArgs[Main.SECOND]));
        tresor.setAxeVertical(parseInt(lineArgs[Main.THIRD]));
        tresor.setNbTresor(parseInt(lineArgs[Main.FOURTH]));
        return tresor;
    }

    private ElementDTO getMontagne(String[] lineArgs) {
        ElementDTO montagne = new ElementDTO();
        montagne.setType(Main.MONTAGNE);
        montagne.setAxeHorizontal(parseInt(lineArgs[Main.SECOND]));
        montagne.setAxeVertical(parseInt(lineArgs[Main.THIRD]));
        return montagne;
    }

    private void initDimensions(String[] lineArgs) {
        int largeur = getLargeurCarte(lineArgs);
        int hauteur = getHauteurCarte(lineArgs);
        dimensions = new DimensionDTO();
        dimensions.setLargeur(largeur);
        dimensions.setHauteur(hauteur);
    }

    private int getHauteurCarte(String[] lineArgs) {
        String hauteurCarte = lineArgs[Main.THIRD];
        return parseInt(hauteurCarte);
    }

    private int getLargeurCarte(String[] lineArgs) {
        String largeurCarte = lineArgs[Main.SECOND];
        return parseInt(largeurCarte);
    }

    public DimensionDTO getDimensions() {
        return dimensions;
    }

    public List<ElementDTO> getElementsRequest() {
        return elementsRequest;
    }
}
