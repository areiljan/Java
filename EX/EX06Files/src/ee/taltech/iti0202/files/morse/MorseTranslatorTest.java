package ee.taltech.iti0202.files.morse;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
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
}