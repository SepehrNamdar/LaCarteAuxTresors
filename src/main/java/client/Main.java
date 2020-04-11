package client;

import application.CarteAuxTresorsGame;
import application.DimensionDTO;
import application.ElementRequest;
import application.ElementResponse;
import exposition.CarteAuxtTresors;

import java.util.List;

import static client.FileHelper.FIRST;
import static client.FileHelper.SECOND;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = args[FIRST];
        String outputFilePath = args[SECOND];

        FileReader inoutFile = new FileReader(inputFilePath);
        inoutFile.read();
        DimensionDTO dimensions = inoutFile.getDimensions();
        List<ElementRequest> elementsRequest = inoutFile.getElementsRequest();

        CarteAuxtTresors carteAuxtTresors = new CarteAuxTresorsGame();
        carteAuxtTresors.play(dimensions, elementsRequest);
        List<ElementResponse> elementsResponse = carteAuxtTresors.getElements();

        FileWriter gameResult = new FileWriter(outputFilePath);
        gameResult.write(dimensions, elementsResponse);
    }

}
