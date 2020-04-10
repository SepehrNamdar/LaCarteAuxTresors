package client;

import application.CarteAuxTresorsGame;
import application.DimensionDTO;
import application.ElementDTO;
import exposition.CarteAuxtTresors;

import java.util.List;

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

    public static void main(String[] args) {
        String inputFilePath = args[FIRST];
        String outputFilePath = args[SECOND];

        Reader inputFileReader = new Reader();
        inputFileReader.read(inputFilePath);
        DimensionDTO dimensions = inputFileReader.getDimensions();
        List<ElementDTO> elementsRequest = inputFileReader.getElementsRequest();

        CarteAuxtTresors carteAuxtTresors = new CarteAuxTresorsGame();
        carteAuxtTresors.play(dimensions, elementsRequest);
        List<ElementDTO> elementsResponse = carteAuxtTresors.getElements();

        FileWriter.write(outputFilePath, dimensions, elementsResponse);
    }

}
