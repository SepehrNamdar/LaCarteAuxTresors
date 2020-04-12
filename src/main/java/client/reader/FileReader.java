package client.reader;

import application.DimensionDTO;
import client.reader.CanNotReadInputFile;
import common.ElementRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static client.FileHelper.*;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

public class FileReader {

    private DimensionDTO dimensions;
    private final List<ElementRequest> elementsRequest;
    private final String inputFilePath;

    public FileReader(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        elementsRequest = new ArrayList<>();
        dimensions = new DimensionDTO();
    }

    public void read() {
        try (Stream<String> lines = lines(get(inputFilePath))) {
            initCarteDimensionsAndElements(lines);
        } catch (IOException e) {
            throw new CanNotReadInputFile(e.getMessage());
        }
    }

    private void initCarteDimensionsAndElements(Stream<String> lines) {
        lines.forEach(line -> {
            if (isNotComment(line)) {
                process(line);
            }
        });
    }

    public void process(String line) {
        String[] lineArgs = line.split(SEPARATOR);
        String firstArg = lineArgs[FIRST];
        if (isCarte(firstArg)) {
            initDimensions(lineArgs);
        } else {
            processElement(lineArgs, firstArg);
        }
    }

    private void processElement(String[] lineArgs, String firstArg) {
        if (isMontagne(firstArg)) {
            elementsRequest.add(getMontagne(lineArgs));
        } else if (isTresor(firstArg)) {
            elementsRequest.add(getTresor(lineArgs));
        } else if (isAventurier(firstArg)) {
            elementsRequest.add(getAventurier(lineArgs));
        }
    }

    private boolean isNotComment(String line) {
        return !line.isEmpty() && !COMMENT.equals(valueOf(line.charAt(FIRST)));
    }

    private boolean isAventurier(String lineArg) {
        return AVENTURIER.equals(lineArg);
    }

    private boolean isTresor(String lineArg) {
        return TRESOR.equals(lineArg);
    }

    private boolean isMontagne(String lineArg) {
        return MONTAGNE.equals(lineArg);
    }

    private boolean isCarte(String lineArg) {
        return CARTE.equals(lineArg);
    }

    private ElementRequest getAventurier(String[] lineArgs) {
        ElementRequest aventurier = new ElementRequest();
        aventurier.setType(AVENTURIER);
        aventurier.setName(lineArgs[SECOND]);
        aventurier.setAxeHorizontal(parseInt(lineArgs[THIRD]));
        aventurier.setAxeVertical(parseInt(lineArgs[FOURTH]));
        aventurier.setOrientation(lineArgs[FIFTH]);
        aventurier.setMouvements(lineArgs[SIXTH]);
        return aventurier;
    }

    private ElementRequest getTresor(String[] lineArgs) {
        ElementRequest tresor = new ElementRequest();
        tresor.setType(TRESOR);
        tresor.setAxeHorizontal(parseInt(lineArgs[SECOND]));
        tresor.setAxeVertical(parseInt(lineArgs[THIRD]));
        tresor.setNbTresor(parseInt(lineArgs[FOURTH]));
        return tresor;
    }

    private ElementRequest getMontagne(String[] lineArgs) {
        ElementRequest montagne = new ElementRequest();
        montagne.setType(MONTAGNE);
        montagne.setAxeHorizontal(parseInt(lineArgs[SECOND]));
        montagne.setAxeVertical(parseInt(lineArgs[THIRD]));
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
        String hauteurCarte = lineArgs[THIRD];
        return parseInt(hauteurCarte);
    }

    private int getLargeurCarte(String[] lineArgs) {
        String largeurCarte = lineArgs[SECOND];
        return parseInt(largeurCarte);
    }

    public DimensionDTO getDimensions() {
        return dimensions;
    }

    public List<ElementRequest> getElementsRequest() {
        return elementsRequest;
    }
}
