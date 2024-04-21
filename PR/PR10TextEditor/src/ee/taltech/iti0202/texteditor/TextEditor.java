package ee.taltech.iti0202.texteditor;

import ee.taltech.iti0202.texteditor.textformatter.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            List<String> subList = textsBuffer.subList(0, index);
            textsBuffer = new ArrayList<>(subList);
            textsBuffer.add(text);
            history.add(text);
            index = textsBuffer.size();
        } else {
            List<String> subList = textsBuffer.subList(0, index);
            textsBuffer = new ArrayList<>(subList);
            textsBuffer.add(strategy.format(text));
            history.add(strategy.format(text));
            index = textsBuffer.size();
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
        if (!textsBuffer.isEmpty()) {
            for (int i = 1; i < index + 1; i++) {
                allText.append(textsBuffer.get(i - 1));
                allText.append(" ");
            }
        }
        return allText.toString();
    }

    /**
     * Go one entry back in the buffer.
     * @return - current text.
     */
    public String undo() {
        if (index > 0) {
            index -= 1;
        }
        return getCurrentText();
    }

    /**
     * Go one entry forward in the buffer.
     * @return - current text.
     */
    public String redo() {
        if (index < textsBuffer.size()) {
            index += 1;
        }
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
}
