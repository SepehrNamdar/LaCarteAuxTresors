import application.CarteAuxTresorsGame;
import application.DimensionDTO;
import application.ElementDTO;
import exposition.CarteAuxtTresors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Main {

    public static final String SEPARATOR = " - ";
    public static final String CARTE = "C";
    public static final String MONTAGNE = "M";
    public static final String TRESOR = "T";
    public static final String AVENTURIER = "A";
    public static final String END_LINE = "\n";

    private static int LARGEUR = 0;
    private static int HAUTEUR = 0;

    public static void main(String[] args) {
        String fileName = args[0];
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<ElementDTO> elementsDTO = new ArrayList<>();
            DimensionDTO dimensionsDTO = new DimensionDTO();
            stream.forEach(line -> {
                String[] lineArgs = line.split(SEPARATOR);
                if (CARTE.equals(lineArgs[0])) {
                    int largeur = parseInt(lineArgs[1]);
                    dimensionsDTO.setLargeur(largeur);
                    int hauteur = parseInt(lineArgs[2]);
                    dimensionsDTO.setHauteur(hauteur);
                    LARGEUR = largeur;
                    HAUTEUR = hauteur;
                } else if (MONTAGNE.equals(lineArgs[0])) {
                    ElementDTO montagne = new ElementDTO();
                    montagne.setType(MONTAGNE);
                    montagne.setAxeHorizontal(parseInt(lineArgs[1]));
                    montagne.setAxeVertical(parseInt(lineArgs[2]));
                    elementsDTO.add(montagne);
                } else if (TRESOR.equals(lineArgs[0])) {
                    ElementDTO tresor = new ElementDTO();
                    tresor.setType(TRESOR);
                    tresor.setAxeHorizontal(parseInt(lineArgs[1]));
                    tresor.setAxeVertical(parseInt(lineArgs[2]));
                    tresor.setNbTresor(parseInt(lineArgs[3]));
                    elementsDTO.add(tresor);
                } else if (AVENTURIER.equals(lineArgs[0])) {
                    ElementDTO aventurier = new ElementDTO();
                    aventurier.setType(AVENTURIER);
                    aventurier.setName(lineArgs[1]);
                    aventurier.setAxeHorizontal(parseInt(lineArgs[2]));
                    aventurier.setAxeVertical(parseInt(lineArgs[3]));
                    aventurier.setOrientation(lineArgs[4]);
                    aventurier.setMouvements(lineArgs[5]);
                    aventurier.setNbTresor(0);
                    elementsDTO.add(aventurier);
                }
            });

            CarteAuxtTresors carteAuxtTresors = new CarteAuxTresorsGame();
            carteAuxtTresors.play(dimensionsDTO, elementsDTO);
            List<ElementDTO> elements = carteAuxtTresors.getElements();
            Files.write(Paths.get(args[1]), tracer(elements).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String tracer(List<ElementDTO> elementsDto) {
        StringBuilder result = new StringBuilder();
        result.append(CARTE + SEPARATOR).append(LARGEUR).append(SEPARATOR).append(HAUTEUR).append(END_LINE);
        for (ElementDTO eltDto : elementsDto) {
            String type = eltDto.getType();
            int axeHorizontal = eltDto.getAxeHorizontal();
            int axeVertical = eltDto.getAxeVertical();
            if (MONTAGNE.equals(type)) {
                result.append(MONTAGNE + SEPARATOR).append(axeHorizontal).append(SEPARATOR).append(axeVertical).append(END_LINE);
            } else {
                int nbTresor = eltDto.getNbTresor();
                if (TRESOR.equals(type)) {
                    result.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}").append(END_LINE);
                    result.append(TRESOR + SEPARATOR).append(axeHorizontal).append(SEPARATOR).append(axeVertical).append(SEPARATOR).append(nbTresor).append(END_LINE);
                } else if (AVENTURIER.equals(type)) {
                    result.append("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}").append(END_LINE);
                    result.append(AVENTURIER + SEPARATOR).append(eltDto.getNom()).append(SEPARATOR).append(axeHorizontal).append(SEPARATOR).append(axeVertical).append(SEPARATOR).append(eltDto.getOrientation()).append(SEPARATOR).append(nbTresor).append(END_LINE);
                }
            }
        }
        return result.toString();
    }

}
