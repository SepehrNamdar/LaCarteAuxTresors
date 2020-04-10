package client;

import application.DimensionDTO;
import application.ElementDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.Paths.get;

public class FileWriter {

    private static DimensionDTO dimensions;
    private static List<ElementDTO> elements;

    static void write(String outputFilePath, DimensionDTO dimensions, List<ElementDTO> elements) {
        FileWriter.dimensions = dimensions;
        FileWriter.elements = elements;
        try {
            Files.write(get(outputFilePath), traceElementsOnCarte().getBytes());
        } catch (IOException e) {
            throw new CanNotReadInputFile(e.getMessage());
        }
    }

    private static String traceElementsOnCarte() {
        StringBuilder result = new StringBuilder();
        result.append(Main.CARTE)
                .append(Main.SEPARATOR)
                .append(dimensions.getLargeur())
                .append(Main.SEPARATOR)
                .append(dimensions.getHauteur())
                .append(Main.END_LINE);
        for (ElementDTO eltDto : elements) {
            String type = eltDto.getType();
            int axeHorizontal = eltDto.getAxeHorizontal();
            int axeVertical = eltDto.getAxeVertical();
            if (Main.MONTAGNE.equals(type)) {
                result.append(Main.MONTAGNE)
                        .append(Main.SEPARATOR)
                        .append(axeHorizontal)
                        .append(Main.SEPARATOR)
                        .append(axeVertical)
                        .append(Main.END_LINE);
            } else {
                int nbTresor = eltDto.getNbTresor();
                if (Main.TRESOR.equals(type)) {
                    result.append(Main.COMMENT)
                            .append(" {")
                            .append("T")
                            .append(" comme Trésor}")
                            .append(Main.SEPARATOR)
                            .append("{Axe horizontal}")
                            .append(Main.SEPARATOR)
                            .append("{Axe vertical}")
                            .append(Main.SEPARATOR)
                            .append("{Nb. de trésors restants}")
                            .append(Main.END_LINE);
                    result.append(Main.TRESOR + Main.SEPARATOR)
                            .append(axeHorizontal)
                            .append(Main.SEPARATOR)
                            .append(axeVertical)
                            .append(Main.SEPARATOR)
                            .append(nbTresor)
                            .append(Main.END_LINE);
                } else if (Main.AVENTURIER.equals(type)) {
                    result.append(Main.COMMENT)
                            .append(" {")
                            .append(Main.AVENTURIER)
                            .append(" comme Aventurier}")
                            .append(Main.SEPARATOR)
                            .append("Nom de l’aventurier")
                            .append(Main.SEPARATOR)
                            .append("{Axe horizontal}")
                            .append(Main.SEPARATOR)
                            .append("{Axe vertical}")
                            .append(Main.SEPARATOR)
                            .append("{Orientation}")
                            .append(Main.SEPARATOR)
                            .append("{Nb. de trésors ramassés}")
                            .append(Main.END_LINE);
                    result.append(Main.AVENTURIER)
                            .append(Main.SEPARATOR)
                            .append(eltDto.getNom())
                            .append(Main.SEPARATOR)
                            .append(axeHorizontal)
                            .append(Main.SEPARATOR)
                            .append(axeVertical)
                            .append(Main.SEPARATOR)
                            .append(eltDto.getOrientation())
                            .append(Main.SEPARATOR)
                            .append(nbTresor)
                            .append(Main.END_LINE);
                }
            }
        }
        return result.toString();
    }
}
