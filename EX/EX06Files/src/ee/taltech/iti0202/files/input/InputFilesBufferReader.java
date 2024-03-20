package ee.taltech.iti0202.files.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFilesBufferReader implements InputFilesReader {
    /**
     * Read text from file using the BufferedReader.
     */
    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> readLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileReaderException("No such file", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readLines;
    }
}
