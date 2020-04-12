package client.reader;

import client.ElementReader;
import common.ElementDTO;

import static client.FileHelper.*;
import static java.lang.Integer.parseInt;

public class MontagneReader extends ElementReader {
    @Override
    public ElementDTO read(String[] lineArgs) {
        ElementDTO montagne = new ElementDTO();
        montagne.setType(MONTAGNE);
        montagne.setAxeHorizontal(parseInt(lineArgs[SECOND]));
        montagne.setAxeVertical(parseInt(lineArgs[THIRD]));
        return montagne;
    }
}
