package ee.taltech.iti0202.texteditor;

import ee.taltech.iti0202.texteditor.textformatter.*;

import java.util.ArrayList;
import java.util.Collection;

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
        this.editorType = TextType.PLAIN;
    }

    /**
     * Different types of text formatting styles.
     */
    public enum TextType {
        PLAIN, SCREAMING, TITLE, CAMELCASE, BINARY
    }

    /**
     * Add a text with the current text formatting to the TextEditor.
     * @param text - text to edit.
     */
    public void addText(String text) {
        if (strategy.equals(null)) {
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
        return textsBuffer.get(index);
    }

    /**
     * Go one entry back in the buffer.
     * @return - current text.
     */
    public String undo() {
        index -= 0;
        return textsBuffer.get(index);
    }

    /**
     * Go one entry forward in the buffer.
     * @return - current text.
     */
    public String redo() {
        index += 1;
        return textsBuffer.get(index);
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
        if (type.equals(TextType.PLAIN)) {
            strategy = null;
        } else if (type.equals(TextType.SCREAMING)) {
            strategy = new UppercaseFormatter();
        } else if (type.equals(TextType.CAMELCASE)) {
            strategy = new CamelCaseFormatter();
        } else if (type.equals(TextType.BINARY)) {
            strategy = new BinaryCaseFormatter();
        } else if (type.equals(TextType.TITLE)) {
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
}
