package ee.taltech.iti0202.texteditor.textformatter;

public class CamelCaseFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        StringBuilder camelCaseString = new StringBuilder();
        String[] words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue; // Skip empty strings
            }
            if (i == 0) {
                camelCaseString.append(word.toLowerCase());
            } else {
                // Check if the previous character is a symbol and should be retained
                boolean retainPreviousSymbol = false;
                if (i > 1 && word.length() >= 2) {
                    char prevChar = word.charAt(word.length() - 2);
                    char lastChar = word.charAt(word.length() - 1);
                    if ((prevChar == '\n' || !Character.isLetterOrDigit(prevChar)) && (Character.isLetterOrDigit(lastChar) || lastChar == '\n')) {
                        retainPreviousSymbol = true;
                    }
                } else if (i > 0 && word.length() >= 1) {
                    char prevChar = words[i - 1].charAt(words[i - 1].length() - 1);
                    if (!Character.isLetterOrDigit(prevChar) && prevChar != '.' && prevChar != '\n') {
                        retainPreviousSymbol = true;
                    }
                }
                // Append the current word in camel case format
                camelCaseString.append(retainPreviousSymbol ? word.substring(0, word.length() - 1) : word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase());
            }
        }
        return camelCaseString.toString();
    }
}
