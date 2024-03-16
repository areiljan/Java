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
        StringBuilder morseCodeBuilder = new StringBuilder();

        for (Character letter : letters) {
            // Check if the character exists in the morseTranslator map
            String translation = morseTranslator.get(letter);

            if (translation != null) {
                morseCodeBuilder.append(translation).append(" ");
            } else {
                // Handle characters not found in the map (e.g., skip or provide default translation)
                // For now, we'll just skip the character
                System.out.println("Character '" + letter + "' not found in the translator. Skipping.");
            }
        }

        return morseCodeBuilder.toString();
    }


    private String translateLineFromMorse(String line) {
        String unicodeText = "";
        if (line != null) {
            String[] words = line.split("   ");
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