package ee.taltech.iti0202.texteditor.textformatter;

public class CamelCaseFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        StringBuilder camelCaseString = new StringBuilder();

//        // Replace unwanted symbols with a single space, but not those at the end of the string or before a newline
//        String cleanedText = text.replaceAll("[^a-zA-Z0-9\\n](?!$|\\n)", " ");
//        cleanedText = cleanedText.replaceAll("[^a-zA-Z0-9\n]+(?=\n)", "");

        String cleanedText = text.replaceAll("[^a-zA-Z0-9\\.\\n]|\n(?!$)", " ");
        cleanedText.replaceAll("(\\n)(?!.*\\1)", "");

        // Split the cleaned text into words
        String[] words = cleanedText.split(" ");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue; // Skip empty strings
            }

            if (i == 0) {
                // If it's the first word, convert it to lowercase
                camelCaseString.append(word.toLowerCase());
            } else {
                // Convert the first character to uppercase and the rest to lowercase
                camelCaseString.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase());
            }
        }

        return camelCaseString.toString();
    }
}
