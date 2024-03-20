package ee.taltech.iti0202.files.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFilesLines implements InputFilesReader {
    /**
     * Read text from file using the Files.lines.
     * @param filename - file to read from.
     * @return - read lines.
     */
    @Override
    public List<String> readTextFromFile(String filename) {
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException("No such file.", e);
        }
    }
}