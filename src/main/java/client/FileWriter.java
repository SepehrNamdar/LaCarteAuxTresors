package client;

import common.dto.DimensionDTO;
import client.writer.CanNotWriteOutputFile;
import common.dto.ElementDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.Paths.get;

public class FileWriter {

    private DimensionDTO dimensions;
    private List<ElementDTO> elements;
    private final String outputFilePath;

    public FileWriter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public void write(DimensionDTO dimensions, List<ElementDTO> elements) {
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
        result.append(ElementWriter.getCarteToWrite(dimensions));
        for (ElementDTO elt : elements) {
            result.append(ElementWriter.getElementToWrite(elt));
        }
        return result.toString();
    }
}
