package ee.taltech.iti0202.files.input;

import org.junit.jupiter.api.Assertions;

import java.util.List;

class InputFilesReaderTest {
    @org.junit.jupiter.api.Test
    void InputFilesLines() {
        InputFilesReader reader = new InputFilesBufferReader();
        List<String> readlines = reader.readTextFromFile("morse.txt");
        Assertions.assertEquals("helloworld", readlines); //key and value
    }
}