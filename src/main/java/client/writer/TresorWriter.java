package client.writer;

import client.ElementWriter;
import common.ElementDTO;

import static client.FileHelper.*;

public class TresorWriter extends ElementWriter {
    @Override
    public StringBuilder getLine(ElementDTO elt) {
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
}
