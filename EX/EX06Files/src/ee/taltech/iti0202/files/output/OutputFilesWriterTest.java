package ee.taltech.iti0202.files.output;

import ee.taltech.iti0202.files.input.InputFilesBufferReader;
import ee.taltech.iti0202.files.input.InputFilesReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OutputFilesWriterTest {

    @Test
    void writeLinesToFile() {
         OutputFilesWriter writer = new OutputFilesWriter();
         InputFilesReader reader = new InputFilesBufferReader();
         List<String> normalLines = new ArrayList<>();
         normalLines.add("Hi, man!");
         normalLines.add("How is it going?");
         writer.writeLinesToFile(normalLines, "output.txt");
        Assertions.assertEquals(reader.readTextFromFile("output.txt"), normalLines);
    }
}