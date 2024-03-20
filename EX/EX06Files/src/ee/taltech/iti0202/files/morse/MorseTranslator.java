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
    public String translateLineToMorse(String line) {
        if (line == null || line.isEmpty()) {
            System.out.println("Input line is empty or null.");
        }

        String[] words = line.split(" ");
        StringBuilder morseCodeBuilder = new StringBuilder();

        for (String word : words) {
            if (!morseCodeBuilder.isEmpty()) {
                morseCodeBuilder.append("\t");
            }
            char[] letters = word.toCharArray();
            for (int i = 0; i < letters.length; i++) {
                // Check if the character exists in the morseTranslator map
                Character letter = letters[i];
                String translation = morseTranslator.get(letter.toString().toLowerCase());

                if ((translation != null)) {
                    morseCodeBuilder.append(translation);
                }
                // If not the last character.
                if (!(i == (letters.length - 1))) {
                    morseCodeBuilder.append(" ");
                }
            }
        }
        return morseCodeBuilder.toString();
    }


    /**
     * Translate a line into UniCode using the MorseTranslator.
     * @param line - the Morse line to translate.
     * @return - line as UniCode string.
     */
    public String translateLineFromMorse(String line) {
        StringBuilder unicodeText = new StringBuilder();
        if (line != null) {
            String[] words = line.split("\\s{2,}");
            for (String word : words) {
                // Add a space to the beginning of each word, except the first one.
                if (!unicodeText.isEmpty()) {
                    unicodeText.append(" ");
                }
                String[] symbols = word.split(" ");
                for (String symbol : symbols) {
                    for (String key : morseTranslator.keySet()) {
                        if (morseTranslator.get(key).equals(symbol.toLowerCase())) {
                            unicodeText.append(key);
                        }
                    }
                }
            }
        }
        return unicodeText.toString();
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
            String translatedLine = translateLineFromMorse(line) + "\\t";
            unicodeLines.add(translateLineFromMorse(line));
        }
        return unicodeLines;
    }
}