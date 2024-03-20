package ee.taltech.iti0202.files.input;

import java.util.List;

public interface InputFilesReader {
    /**
     * Interface for the FilesReaders
     * @param filename - what file to read.
     * @return - lines read.
     */
    List<String> readTextFromFile(String filename);
}
