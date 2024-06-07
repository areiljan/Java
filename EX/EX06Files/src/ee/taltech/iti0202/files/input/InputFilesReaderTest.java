package ee.taltech.iti0202.files.input;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InputFilesReaderTest {
    @org.junit.jupiter.api.Test
    void inputFilesLines() {
        InputFilesReader reader = new InputFilesLines();
        List<String> readLines = reader.readTextFromFile("input.txt");
        List<String> expectedReadLines = new ArrayList<>();
        expectedReadLines.add("hello");
        expectedReadLines.add("world");
        Assertions.assertEquals(expectedReadLines, readLines);
    }

    @org.junit.jupiter.api.Test
    void inputFilesLinesThrowNoSuchFile() {
        InputFilesReader reader = new InputFilesLines();
        assertThrows(FileReaderException.class, () -> reader.readTextFromFile("doodoo.txt"));
    }

    @org.junit.jupiter.api.Test
    void inputFilesBufferReader() {
        InputFilesReader reader = new InputFilesBufferReader();
        List<String> readLines = reader.readTextFromFile("input.txt");
        List<String> expectedReadLines = new ArrayList<>();
        expectedReadLines.add("hello");
        expectedReadLines.add("world");
        Assertions.assertEquals(expectedReadLines, readLines);
    }

    @org.junit.jupiter.api.Test
    void inputFilesBufferReaderThrowNoSuchFile() {
        InputFilesReader reader = new InputFilesBufferReader();
        assertThrows(FileReaderException.class, () -> reader.readTextFromFile("doodoo.txt"));
    }

    @org.junit.jupiter.api.Test
    void inputFilesScanner() {
        InputFilesReader reader = new InputFilesScanner();
        List<String> readLines = reader.readTextFromFile("input.txt");
        List<String> expectedReadLines = new ArrayList<>();
        expectedReadLines.add("hello");
        expectedReadLines.add("world");
        Assertions.assertEquals(expectedReadLines, readLines);
    }

    @org.junit.jupiter.api.Test
    void inputFilesScannerThrowNoSuchFile() {
        InputFilesReader reader = new InputFilesScanner();
        assertThrows(FileReaderException.class, () -> reader.readTextFromFile("doodoo.txt"));
    }
}
