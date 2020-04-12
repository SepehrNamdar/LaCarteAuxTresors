package client.writer;

import client.ElementWriter;
import common.ElementDTO;

import static client.FileHelper.*;

public class MontagneWriter extends ElementWriter {
    @Override
    public StringBuilder getLine(ElementDTO elt) {
        StringBuilder result = new StringBuilder();
        result.append(MONTAGNE)
                .append(SEPARATOR)
                .append(elt.getAxeHorizontal())
                .append(SEPARATOR)
                .append(elt.getAxeVertical())
                .append(END_LINE);
        return result;
    }
}
