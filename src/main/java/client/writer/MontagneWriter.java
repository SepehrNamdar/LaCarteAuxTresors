package client.writer;

import common.ElementWriter;
import common.ElementResponse;

import static client.FileHelper.*;

public class MontagneWriter implements ElementWriter {
    @Override
    public StringBuilder getLine(ElementResponse elt) {
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
