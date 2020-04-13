package client;

import common.dto.DimensionDTO;
import client.reader.CanNotReadInputFile;
import common.dto.ElementDTO;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static client.FileHelper.*;
import static java.lang.String.valueOf;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

public class FileReader {

    private final String inputFilePath;

    public FileReader(final String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void read() {
        try (Stream<String> lines = lines(get(inputFilePath))) {
            initCarteDimensionsAndElements(lines);
        } catch (IOException e) {
            throw new CanNotReadInputFile(e.getMessage());
        }
    }

    private void initCarteDimensionsAndElements(final Stream<String> lines) {
        lines.forEach(line -> {
            if (isNotComment(line)) {
                process(line);
            }
        });
    }

    public static void process(final String line) {
        String[] lineArgs = line.split(SEPARATOR);
        if (isCarte(lineArgs[FIRST])) {
            ElementReader.processDimensions(lineArgs);
        } else {
            ElementReader.processElement(lineArgs);
        }
    }

    private static boolean isCarte(final String lineArg) {
        return CARTE.equals(lineArg);
    }

    private boolean isNotComment(final String line) {
        return !line.isEmpty() && !COMMENT.equals(valueOf(line.charAt(FIRST)));
    }

    public DimensionDTO getDimensions() {
        return ElementReader.getDimensions();
    }

    public List<ElementDTO> getElements() {
        return ElementReader.getElements();
    }
}
