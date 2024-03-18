package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {
    private Map<String, String> morseTranslator;

    /**
     * MorseTranslator constructor.
     */
    public MorseTranslator() {
        this.morseTranslator = null;
    }

    /**
     * Add the morse codes from which to do the translations.
     * @param lines - the lines of Morse and Unicode pairs.
     * @return - the map of pairs.
     */
    public Map<String, String> addMorseCodes(List<String> lines) {
        Map<String, String> morseTranslator = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            morseTranslator.put(parts[0].toLowerCase(), parts[1]);
        }
        this.morseTranslator = morseTranslator;
        return morseTranslator;
    }

    /**
     * Translate a line into morse using the MorseTranslator.
     * @param line - the UniCode line to translate.
     * @return - line as morse string.
     */
    private String translateLineToMorse(String line) {
        if (line == null || line.isEmpty()) {
            System.out.println("Input line is empty or null.");
            return ""; // Or handle this case according to your requirements
        }

        String lineWithoutSpaces = line.replaceAll("\\s+", "");
        char[] letters = lineWithoutSpaces.toCharArray();
        StringBuilder morseCodeBuilder = new StringBuilder();

        for (int i = 0; i < letters.length; i++) {
            // Check if the character exists in the morseTranslator map
            Character letter = letters[i];
            String translation = morseTranslator.get(letter.toString());

            if ((translation != null)) {
                morseCodeBuilder.append(translation);
            }
            if (!(i == (letters.length - 1))) {
                morseCodeBuilder.append(" ");
            }
        }
        return morseCodeBuilder.toString();
    }


    /**
     * Translate a line into UniCode using the MorseTranslator.
     * @param line - the Morse line to translate.
     * @return - line as UniCode string.
     */
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

    /**
     * Translate multiple lines to morse.
     * @param lines - the lines to translate.
     * @return - list of translated lines.
     */
    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> morseLines = new ArrayList<>();
        for (String line : lines) {
            morseLines.add(translateLineToMorse(line));
        }
        return morseLines;
    }

    /**
     * Translate multiple lines to UniCode.
     * @param lines - List of lines to translate.
     * @return - List of lines in unicode.
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> unicodeLines = new ArrayList<>();
        for (String line : lines) {
            unicodeLines.add(translateLineFromMorse(line));
        }
        return unicodeLines;
    }
}