package client;

import common.DimensionDTO;
import client.writer.AventurierWriter;
import client.writer.MontagneWriter;
import client.writer.TresorWriter;
import common.ElementDTO;

import static client.FileHelper.*;

public abstract class ElementWriter {
    public abstract StringBuilder getLine(ElementDTO elt);

    public static StringBuilder getCarteToWrite(DimensionDTO dimensions) {
        StringBuilder result = new StringBuilder();
        result.append(CARTE)
                .append(SEPARATOR)
                .append(dimensions.getLargeur())
                .append(SEPARATOR)
                .append(dimensions.getHauteur())
                .append(END_LINE);
        return result;
    }

    public static StringBuilder getElementToWrite(ElementDTO elt) {
        String eltType = elt.getType();
        StringBuilder result = new StringBuilder();
        switch (eltType) {
            case MONTAGNE:
                ElementWriter montagneWriter = new MontagneWriter();
                result.append(montagneWriter.getLine(elt));
                break;
            case TRESOR:
                ElementWriter tresorWriter = new TresorWriter();
                result.append(tresorWriter.getLine(elt));
                break;
            case AVENTURIER:
                ElementWriter aventurierWriter = new AventurierWriter();
                result.append(aventurierWriter.getLine(elt));
                break;
        }
        return result;
    }
}
