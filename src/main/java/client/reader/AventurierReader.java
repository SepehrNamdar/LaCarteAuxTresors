package client.reader;

import client.ElementReader;
import common.ElementDTO;

import static client.FileHelper.*;
import static client.FileHelper.SIXTH;
import static java.lang.Integer.parseInt;

public class AventurierReader extends ElementReader {
    @Override
    public ElementDTO read(String[] lineArgs) {
        ElementDTO aventurier = new ElementDTO();
        aventurier.setType(AVENTURIER);
        aventurier.setName(lineArgs[SECOND]);
        aventurier.setAxeHorizontal(parseInt(lineArgs[THIRD]));
        aventurier.setAxeVertical(parseInt(lineArgs[FOURTH]));
        aventurier.setOrientation(lineArgs[FIFTH]);
        aventurier.setMouvements(lineArgs[SIXTH]);
        return aventurier;
    }
}
