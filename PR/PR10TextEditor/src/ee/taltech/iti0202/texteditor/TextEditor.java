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
            allText.append(textsBuffer.get(i));
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
        if (type.equals(TextType.PLAIN)) {
            strategy = null;
        } else if (type.equals(TextType.SCREAMING)) {
            strategy = new UppercaseFormatter();
        } else if (type.equals(TextType.CAMELCASE)) {
            strategy = new CamelCaseFormatter();
        } else if (type.equals(TextType.BINARY)) {
            strategy = new BinaryFormatter();
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
