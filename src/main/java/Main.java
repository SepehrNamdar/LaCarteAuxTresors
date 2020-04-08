import application.CarteAuxTresorsGame;
import application.DimensionDTO;
import application.ElementDTO;
import exposition.CarteAuxtTresors;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

public class Main {

    public static final String SEPARATOR = " - ";
    public static final String CARTE = "C";
    public static final String MONTAGNE = "M";
    public static final String TRESOR = "T";
    public static final String AVENTURIER = "A";
    public static final String END_LINE = "\n";
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;
    public static final int SIXTH = 5;
    public static final String COMMENT = "#";

    private static DimensionDTO dimensions;
    private static List<ElementDTO> elementsRequest;

    public static void main(String[] args) {
        String inputFilePath = args[FIRST];
        String outPutFilePath = args[SECOND];

        try (Stream<String> lines = lines(get(inputFilePath))) {
            initCarteDimensionsAndElements(lines);

            CarteAuxtTresors carteAuxtTresors = new CarteAuxTresorsGame();
            carteAuxtTresors.play(dimensions, elementsRequest);
            List<ElementDTO> elementsResponse = carteAuxtTresors.getElements();

            write(outPutFilePath, elementsResponse);
        } catch (IOException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    private static void write(String outputFilePath, List<ElementDTO> elements) throws IOException {
        Files.write(get(outputFilePath), traceElementsOnCarte(elements).getBytes());
    }

    private static void initCarteDimensionsAndElements(Stream<String> lines) {
        elementsRequest = new ArrayList<>();
        lines.forEach(line -> {
            if (isNotComment(line)) {
                process(line);
            }
        });
    }

    private static void process(String line) {
        String[] lineArgs = line.split(SEPARATOR);
        String firstArg = lineArgs[FIRST];
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

    private static boolean isNotComment(String line) {
        return !line.isEmpty() && !COMMENT.equals(valueOf(line.charAt(FIRST)));
    }

    private static boolean isAventurier(String lineArg) {
        return AVENTURIER.equals(lineArg);
    }

    private static boolean isTresor(String lineArg) {
        return TRESOR.equals(lineArg);
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

    private static boolean isMontagne(String lineArg) {
        return MONTAGNE.equals(lineArg);
    }

    private static boolean isCarte(String lineArg) {
        return CARTE.equals(lineArg);
    }

    private static void initDimensions(String[] lineArgs) {
        int largeur = getLargeurCarte(lineArgs);
        int hauteur = getHauteurCarte(lineArgs);
        dimensions = new DimensionDTO();
        dimensions.setLargeur(largeur);
        dimensions.setHauteur(hauteur);
    }

    private static int getHauteurCarte(String[] lineArgs) {
        String hauteurCarte = lineArgs[THIRD];
        return parseInt(hauteurCarte);
    }

    private static int getLargeurCarte(String[] lineArgs) {
        String largeurCarte = lineArgs[SECOND];
        return parseInt(largeurCarte);
    }

    private static String traceElementsOnCarte(List<ElementDTO> elementsDto) {
        StringBuilder result = new StringBuilder();
        result.append(CARTE)
                .append(SEPARATOR)
                .append(dimensions.getLargeur())
                .append(SEPARATOR)
                .append(dimensions.getHauteur())
                .append(END_LINE);
        for (ElementDTO eltDto : elementsDto) {
            String type = eltDto.getType();
            int axeHorizontal = eltDto.getAxeHorizontal();
            int axeVertical = eltDto.getAxeVertical();
            if (MONTAGNE.equals(type)) {
                result.append(MONTAGNE)
                        .append(SEPARATOR)
                        .append(axeHorizontal)
                        .append(SEPARATOR)
                        .append(axeVertical)
                        .append(END_LINE);
            } else {
                int nbTresor = eltDto.getNbTresor();
                if (TRESOR.equals(type)) {
                    result.append(COMMENT)
                            .append(" {")
                            .append("T")
                            .append(" comme Trésor}")
                            .append(SEPARATOR)
                            .append("{Axe horizontal}")
                            .append(SEPARATOR)
                            .append("{Axe vertical}")
                            .append(SEPARATOR)
                            .append("{Nb. de trésors restants}")
                            .append(END_LINE);
                    result.append(TRESOR + SEPARATOR)
                            .append(axeHorizontal)
                            .append(SEPARATOR)
                            .append(axeVertical)
                            .append(SEPARATOR)
                            .append(nbTresor)
                            .append(END_LINE);
                } else if (AVENTURIER.equals(type)) {
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
                            .append(eltDto.getNom())
                            .append(SEPARATOR)
                            .append(axeHorizontal)
                            .append(SEPARATOR)
                            .append(axeVertical)
                            .append(SEPARATOR)
                            .append(eltDto.getOrientation())
                            .append(SEPARATOR)
                            .append(nbTresor)
                            .append(END_LINE);
                }
            }
        }
        return result.toString();
    }

}
