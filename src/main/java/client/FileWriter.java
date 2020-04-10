package client;

import application.DimensionDTO;
import application.ElementDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static client.FileHelper.*;
import static java.nio.file.Paths.get;

public class FileWriter {

    private DimensionDTO dimensions;
    private List<ElementDTO> elements;
    private final String outputFilePath;

    public FileWriter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    void write(DimensionDTO dimensions, List<ElementDTO> elements) {
        this.dimensions = dimensions;
        this.elements = elements;
        try {
            Files.write(get(outputFilePath), traceElementsOnCarte().getBytes());
        } catch (IOException e) {
            throw new CanNotReadInputFile(e.getMessage());
        }
    }

    private String traceElementsOnCarte() {
        StringBuilder result = new StringBuilder();
        result.append(carteLine());
        for (ElementDTO elt : elements) {
            result.append(ElementDTO.createElementDTO(elt));
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
