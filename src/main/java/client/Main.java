package client;

import application.CarteAuxTresorsGame;
import common.DimensionDTO;
import common.ElementDTO;
import exposition.CarteAuxtTresors;

import java.util.List;

import static client.FileHelper.FIRST;
import static client.FileHelper.SECOND;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = args[FIRST];
        String outputFilePath = args[SECOND];

        FileReader inputFile = new FileReader(inputFilePath);
        inputFile.read();
        DimensionDTO dimensions = inputFile.getDimensions();
        List<ElementDTO> elementsRequest = inputFile.getElements();

        CarteAuxtTresors carteAuxtTresors = new CarteAuxTresorsGame();
        carteAuxtTresors.play(dimensions, elementsRequest);
        List<ElementDTO> elementsResponse = carteAuxtTresors.getElements();

        FileWriter gameResult = new FileWriter(outputFilePath);
        gameResult.write(dimensions, elementsResponse);
    }

}
