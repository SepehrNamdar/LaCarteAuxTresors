package client.writer;

import application.DimensionDTO;
import common.ElementResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static client.FileHelper.*;
import static java.nio.file.Paths.get;

public class FileWriter {

    private DimensionDTO dimensions;
    private List<ElementResponse> elements;
    private final String outputFilePath;

    public FileWriter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public void write(DimensionDTO dimensions, List<ElementResponse> elements) {
        this.dimensions = dimensions;
        this.elements = elements;
        try {
            Files.write(get(outputFilePath), traceElementsOnCarte().getBytes());
        } catch (IOException e) {
            throw new CanNotWriteOutputFile(e.getMessage());
        }
    }

    private String traceElementsOnCarte() {
        StringBuilder result = new StringBuilder();
        result.append(carteLine());
        for (ElementResponse elt : elements) {
            result.append(ElementResponse.createElementDTO(elt));
        }
        return result.toString();
    }

    private StringBuilder carteLine() {
        StringBuilder result = new StringBuilder();
        result.append(CARTE)
                .append(SEPARATOR)
                .append(dimensions.getLargeur())
                .append(SEPARATOR)
                .append(dimensions.getHauteur())
                .append(END_LINE);
        return result;
    }
}
