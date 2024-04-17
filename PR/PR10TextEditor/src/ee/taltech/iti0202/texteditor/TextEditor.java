package ee.taltech.iti0202.texteditor;

import ee.taltech.iti0202.texteditor.textformatter.*;

import java.util.ArrayList;
import java.util.Collection;

import static ee.taltech.iti0202.texteditor.TextType.*;

public class TextEditor {
    private ArrayList<String> textsBuffer;
    private ArrayList<String> history;
    private TextFormatter strategy = null;
    private TextType editorType;
    private int index = 0;

    /**
     * The textEditor that holds edited text.
     */
    public TextEditor() {
        this.textsBuffer = new ArrayList<>();
        this.history = new ArrayList<>();
        this.editorType = PLAIN;
    }

    /**
     * Add a text with the current text formatting to the TextEditor.
     * @param text - text to edit.
     */
    public void addText(String text) {
        if (strategy == null) {
            textsBuffer.add(text);
            history.add(text);
        } else {
            textsBuffer.add(strategy.format(text));
            history.add(text);
        }
    }

    /**
     * Add a text the text formatting specified to the TextEditor.
     * @param text - text to edit.
     * @param type - type of text.
     */
    public void addText(String text, TextType type) {
        setStrategy(type);
        addText(text);
    }

    /**
     * Get the current text.
     * @return - text.
     */
    public String getCurrentText() {
        StringBuilder allText = new StringBuilder();
        for (int i = 0; i < index; i++) {
            if (textsBuffer.size() < index) {
                allText.append(textsBuffer.get(i));
            }
        }
        return allText.toString();
    }

    /**
     * Go one entry back in the buffer.
     * @return - current text.
     */
    public String undo() {
        index -= 1;
        return getCurrentText();
    }

    /**
     * Go one entry forward in the buffer.
     * @return - current text.
     */
    public String redo() {
        index += 1;
        return getCurrentText();
    }

    /**
     * Clear the buffer.
     */
    public void clear() {
        textsBuffer.clear();
    }

    /**
     * Set the strategy to use on texts.
     * @param type - type of text editing.
     */
    public void setStrategy(TextType type) {
        if (type.equals(PLAIN)) {
            strategy = null;
        } else if (type.equals(SCREAMING)) {
            strategy = new UppercaseFormatter();
        } else if (type.equals(CAMELCASE)) {
            strategy = new CamelCaseFormatter();
        } else if (type.equals(BINARY)) {
            strategy = new BinaryFormatter();
        } else if (type.equals(TITLE)) {
            strategy = new TitleCaseFormatter();
        }
    }

    /**
     * Get the history of added Strings.
     * @return - List of strings.
     */
    public Collection<String> getHistory() {
        return history;
    }

    /**
     * Get the current applied strategy.
     * @return - current TextFormatter.
     */
    public TextFormatter getStrategy() {
        return strategy;
    }

    class Main {
        public static void main(String[] args) {
            TextEditor textEditor = new TextEditor();
            textEditor.addText("diary of a programmer", TITLE);
            textEditor.addText("Day1: Finally started with my new project today.", PLAIN);
            textEditor.addText("I have \nbeen working on my camelcase.\n", CAMELCASE);
            System.out.println(textEditor.getCurrentText());
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            textEditor.addText("Day2: ", PLAIN);
            textEditor.addText("i'm losing my mind, this bug keeps avoiding meeeeeeeee.\n", SCREAMING);
            textEditor.addText("Day3: ", PLAIN);
            textEditor.addText("help me\n", BINARY);
            System.out.println(textEditor.getCurrentText());
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            // Day2: I'M LOSING MY MIND, THIS BUG KEEPS AVOIDING MEEEEEEEEE. 01101000011001010110110001110000001000000110110101100101
            // Day3: 01101000011001010110110001110000001000000110110101100101
            System.out.println(textEditor.undo());
            System.out.println();
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            // Day2: I'M LOSING MY MIND, THIS BUG KEEPS AVOIDING MEEEEEEEEE.
            // Day3:
            textEditor.addText("Fixed the bug, it was a typo.");
            System.out.println(textEditor.getCurrentText());
            System.out.println();
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            // Day2: I'M LOSING MY MIND, THIS BUG KEEPS AVOIDING MEEEEEEEEE.
            // Day3: 0100011001101001011110000110010101100100001000000111010001101000011001010010000001100010011101010110011100101100001000000110100101110100001000000111011101100001011100110010000001100001001000000111010001111001011100000110111100101110

            textEditor.undo();
            textEditor.setStrategy(PLAIN);
            textEditor.addText("Fixed the bug, it was a typo.");
            System.out.println(textEditor.getCurrentText());
            System.out.println();
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            // Day2: I'M LOSING MY MIND, THIS BUG KEEPS AVOIDING MEEEEEEEEE.
            // Day3: Fixed the bug, it was a typo.

            textEditor.clear();
            System.out.println(textEditor.redo().isEmpty()); // true
        }
    }
}
