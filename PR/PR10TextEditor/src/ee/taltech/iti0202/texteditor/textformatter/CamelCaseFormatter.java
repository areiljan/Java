package ee.taltech.iti0202.texteditor.textformatter;

public class CamelCaseFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        String[] words = text.split("[^\\p{Alnum}]+");
        StringBuilder camelCaseString = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue; // Skip empty strings
            }
            if (i == 0) {
                camelCaseString.append(word.toLowerCase());
            } else {
                boolean retainPreviousPunctuation = false;
                if (i > 1 && word.length() >= 2 && word.charAt(word.length() - 2) == '\n') {
                    retainPreviousPunctuation = true;
                } else if (i > 0 && word.length() >= 1) {
                    char prevChar = words[i - 1].charAt(words[i - 1].length() - 1);
                    if (!Character.isLetterOrDigit(prevChar)) {
                        retainPreviousPunctuation = true;
                    }
                }
                camelCaseString.append(retainPreviousPunctuation ? word.substring(0, word.length() - 2) : word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase());
            }
        }
        return camelCaseString.toString();
    }
}
