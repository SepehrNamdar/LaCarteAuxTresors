package client;

import common.DimensionDTO;
import client.reader.CanNotReadInputFile;
import common.ElementDTO;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static client.FileHelper.*;
import static java.lang.String.valueOf;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

public class FileReader {

    private final String inputFilePath;

    public FileReader(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void read() {
        try (Stream<String> lines = lines(get(inputFilePath))) {
            initCarteDimensionsAndElements(lines);
        } catch (IOException e) {
            throw new CanNotReadInputFile(e.getMessage());
        }
    }

    private void initCarteDimensionsAndElements(Stream<String> lines) {
        lines.forEach(line -> {
            if (isNotComment(line)) {
                process(line);
            }
        });
    }

    public static void process(String line) {
        String[] lineArgs = line.split(SEPARATOR);
        if (isCarte(lineArgs[FIRST])) {
            ElementReader.processDimensions(lineArgs);
        } else {
            ElementReader.processElement(lineArgs);
        }
    }

    private static boolean isCarte(String lineArg) {
        return CARTE.equals(lineArg);
    }

    private boolean isNotComment(String line) {
        return !line.isEmpty() && !COMMENT.equals(valueOf(line.charAt(FIRST)));
    }

    public DimensionDTO getDimensions() {
        return ElementReader.getDimensions();
    }

    public List<ElementDTO> getElements() {
        return ElementReader.getElements();
    }
}
