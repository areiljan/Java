package ee.taltech.iti0202.texteditor.textformatter;

public interface TextFormatter {
    /**
     * Format the text in the right way.
     * @param text - text to format.
     * @return - string in the right format.
     */
    String format(String text);
}
