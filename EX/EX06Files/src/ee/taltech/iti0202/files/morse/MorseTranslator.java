package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {
    private Map<String, String> morseTranslator;

    public MorseTranslator() {
        this.morseTranslator = null;
    }
    public Map<String, String> addMorseCodes(List<String> lines) {
        Map<String, String> morseTranslator = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            morseTranslator.put(parts[0].toLowerCase(), parts[1]);
        }
        this.morseTranslator = morseTranslator;
        return morseTranslator;
    }

    private String translateLineToMorse(String line) {
        String lineWithoutSpaces = line.replaceAll("\\s+", "");
        char[] letters = lineWithoutSpaces.toCharArray();
        String morseCode = "";
        for (Character letter : letters) {
            morseCode += (morseTranslator.get(letter));
            morseCode += (" ");
        }
        return morseCode;
    }

    private String translateLineFromMorse(String line) {
        String[] words = line.split("   ");
        String unicodeText = "";
        for (String word : words) {
            String[] symbols = word.split(" ");
            for (String symbol : symbols) {
                for (String key : morseTranslator.keySet()) {
                    if (morseTranslator.get(key).equals(symbol)) {
                        unicodeText = unicodeText + key;
                    }
                }
            }
            unicodeText = unicodeText + (" ");
        }
        return unicodeText;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> morseLines = new ArrayList<>();
        for (String line : lines) {
            morseLines.add(translateLineFromMorse(line));
        }
        return morseLines;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> unicodeLines = new ArrayList<>();
        for (String line : lines) {
            unicodeLines.add(translateLineFromMorse(line));
        }
        return unicodeLines;
    }
}