package client;

import application.DimensionDTO;
import application.ElementDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static client.FileHelper.*;
import static java.nio.file.Paths.get;

public class FileWriter {

    private DimensionDTO dimensions;
    private List<ElementDTO> elements;
    private final String outputFilePath;

    public FileWriter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    void write(DimensionDTO dimensions, List<ElementDTO> elements) {
        this.dimensions = dimensions;
        this.elements = elements;
        try {
            Files.write(get(outputFilePath), traceElementsOnCarte().getBytes());
        } catch (IOException e) {
            throw new CanNotReadInputFile(e.getMessage());
        }
    }

    private String traceElementsOnCarte() {
        StringBuilder result = new StringBuilder();
        result.append(carteLine());
        for (ElementDTO elt : elements) {
            String eltType = elt.getType();
            if (MONTAGNE.equals(eltType)) {
                result.append(montagneLine(elt));
            } else if (TRESOR.equals(eltType)) {
                result.append(tresorLine(elt));
            } else if (AVENTURIER.equals(eltType)) {
                result.append(aventurierLine(elt));
            }
        }
        return result.toString();
    }

    private StringBuilder aventurierLine(ElementDTO elt) {
        StringBuilder result = new StringBuilder();
        result.append(COMMENT)
                .append(" {")
                .append(AVENTURIER)
                .append(" comme Aventurier}")
                .append(SEPARATOR)
                .append("Nom de l’aventurier")
                .append(SEPARATOR)
                .append("{Axe horizontal}")
                .append(SEPARATOR)
                .append("{Axe vertical}")
                .append(SEPARATOR)
                .append("{Orientation}")
                .append(SEPARATOR)
                .append("{Nb. de trésors ramassés}")
                .append(END_LINE);
        result.append(AVENTURIER)
                .append(SEPARATOR)
                .append(elt.getNom())
                .append(SEPARATOR)
                .append(elt.getAxeHorizontal())
                .append(SEPARATOR)
                .append(elt.getAxeVertical())
                .append(SEPARATOR)
                .append(elt.getOrientation())
                .append(SEPARATOR)
                .append(elt.getNbTresor())
                .append(END_LINE);
        return result;
    }

    private StringBuilder tresorLine(ElementDTO elt) {
        StringBuilder result = new StringBuilder();
        result.append(COMMENT)
                .append(" {")
                .append(TRESOR)
                .append(" comme Trésor}")
                .append(SEPARATOR)
                .append("{Axe horizontal}")
                .append(SEPARATOR)
                .append("{Axe vertical}")
                .append(SEPARATOR)
                .append("{Nb. de trésors restants}")
                .append(END_LINE);
        result.append(TRESOR + SEPARATOR)
                .append(elt.getAxeHorizontal())
                .append(SEPARATOR)
                .append(elt.getAxeVertical())
                .append(SEPARATOR)
                .append(elt.getNbTresor())
                .append(END_LINE);
        return result;
    }

    private StringBuilder montagneLine(ElementDTO eltDto) {
        StringBuilder result = new StringBuilder();
        result.append(MONTAGNE)
                .append(SEPARATOR)
                .append(eltDto.getAxeHorizontal())
                .append(SEPARATOR)
                .append(eltDto.getAxeVertical())
                .append(END_LINE);
        return result;
    }

    private StringBuilder carteLine() {
        StringBuilder result = new StringBuilder();
        result.append(CARTE)
                .append(SEPARATOR)
                .append(dimensions.getLargeur())
                .append(SEPARATOR)
                .append(dimensions.getHauteur())
                .append(END_LINE);
        return result;
    }
}
