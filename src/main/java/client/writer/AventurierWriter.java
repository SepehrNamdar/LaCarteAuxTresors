package client.writer;

import client.ElementWriter;
import common.dto.ElementDTO;

import static client.FileHelper.*;

public class AventurierWriter extends ElementWriter {
    @Override
    public StringBuilder getLine(ElementDTO elt) {
        StringBuilder result = new StringBuilder();
        result.append(COMMENT)
                .append(" {")
                .append(AVENTURIER)
                .append(" comme Aventurier}")
                .append(SEPARATOR)
                .append("{Nom de l’aventurier}")
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
                .append(elt.getName())
                .append(SEPARATOR)
                .append(elt.getAxeHorizontal())
                .append(SEPARATOR)
                .append(elt.getAxeVertical())
                .append(SEPARATOR)
                .append(elt.getOrientation())
                .append(SEPARATOR)
                .append(elt.getNbTresor())
                .append(END_LINE);
        return result;
    }
}
