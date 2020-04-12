package client.reader;

import client.ElementReader;
import common.ElementDTO;

import static client.FileHelper.*;
import static client.FileHelper.FOURTH;
import static java.lang.Integer.parseInt;

public class TresorReader extends ElementReader {
    @Override
    public ElementDTO read(String[] lineArgs) {
        ElementDTO tresor = new ElementDTO();
        tresor.setType(TRESOR);
        tresor.setAxeHorizontal(parseInt(lineArgs[SECOND]));
        tresor.setAxeVertical(parseInt(lineArgs[THIRD]));
        tresor.setNbTresor(parseInt(lineArgs[FOURTH]));
        return tresor;
    }
}
