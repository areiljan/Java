package ee.taltech.iti0202.files.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {
    /**
     * Read lines from file using the Scanner method.
     * @param filename - which file.
     * @return - read files.
     */
    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> textFromFile = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                textFromFile.add(scanner.nextLine());
            }
        } catch (FileReaderException | FileNotFoundException e) {
            throw new FileReaderException("No such file", e);
        }
        return textFromFile;
    }
}