package ee.taltech.iti0202.files.morse;

import ee.taltech.iti0202.files.input.InputFilesBufferReader;
import ee.taltech.iti0202.files.input.InputFilesReader;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MorseTranslatorTest {

    @org.junit.jupiter.api.Test
    void addMorseCodes() {
        MorseTranslator translator = new MorseTranslator();
        ArrayList<String> lines = new ArrayList<>();
        lines.add("A .-");
        lines.add("B -...");
        lines.add("C -.-.");
        Map<String, String> codes = translator.addMorseCodes(lines);
        Assertions.assertEquals(codes.size(), 3); //key and value
        Assertions.assertEquals(codes.get("a"), ".-"); // is the pattern the same
    }

    @org.junit.jupiter.api.Test
    void translateLinesToMorse() {
        MorseTranslator translator = new MorseTranslator();
        ArrayList<String> lines = new ArrayList<>();
        lines.add("A .-");
        lines.add("B -...");
        lines.add("C -.-.");
        translator.addMorseCodes(lines);
        ArrayList<String> lines2 = new ArrayList<>();
        lines2.add("a");
        lines2.add("b");
        lines2.add("c");
        ArrayList<String> morse = (ArrayList<String>) translator.translateLinesToMorse(lines2);
        ArrayList<String> morseToCompare = new ArrayList<>();
        morseToCompare.add(".-");
        morseToCompare.add("-...");
        morseToCompare.add("-.-.");
        Assertions.assertEquals(morse, morseToCompare);
    }

    @org.junit.jupiter.api.Test
    void translateLinesFromMorse() {
        MorseTranslator translator = new MorseTranslator();
        ArrayList<String> lines = new ArrayList<>();
        lines.add("A .-");
        lines.add("B -...");
        lines.add("C -.-.");
        translator.addMorseCodes(lines);
        ArrayList<String> lines2 = new ArrayList<>();
        lines2.add("a");
        lines2.add("b");
        lines2.add("c");
        ArrayList<String> morse = (ArrayList<String>) translator.translateLinesToMorse(lines2);
        ArrayList<String> morseToCompare = new ArrayList<>();
        morseToCompare.add(".-");
        morseToCompare.add("-...");
        morseToCompare.add("-.-.");
        Assertions.assertEquals(morse, morseToCompare);
    }

    @org.junit.jupiter.api.Test
    void translateLinesFromAndBackToMorseMultipleLines() {
        MorseTranslator translator = new MorseTranslator();
        InputFilesReader reader = new InputFilesBufferReader();
        List<String> morseCypher = reader.readTextFromFile("morse.txt");
        ArrayList<String> lines = new ArrayList<>();
        lines.add("lorem ipsum dolor sit amet");
        lines.add("sed do eiusmod tempor.");
        translator.addMorseCodes(morseCypher);
        List<String> morseLines = translator.translateLinesToMorse(lines);
        List<String> excpectedMorseLines = new ArrayList<>();
        excpectedMorseLines.add(".-.. --- .-. . --    .. .--. ... ..- --    -.. --- .-.. --- .-.    ... .. -    .- -- . -");
        excpectedMorseLines.add("... . -..    -.. ---    . .. ..- ... -- --- -..    - . -- .--. --- .-. .-.-.-");
        List<String> textLines = translator.translateLinesFromMorse(morseLines);
        Assertions.assertEquals(lines, textLines);

        List <String> excpectedMorseLinesWithoutSpaces = new ArrayList<>();
        for (String line : excpectedMorseLines) {
            excpectedMorseLinesWithoutSpaces.add(line.replaceAll("\\s", ""));
        }
        List <String> morseLinesWithoutSpaces = new ArrayList<>();
        for (String line : morseLines) {
            morseLinesWithoutSpaces.add(line.replaceAll("\\s", ""));
        }
        Assertions.assertEquals(excpectedMorseLinesWithoutSpaces, morseLinesWithoutSpaces);
    }
}
