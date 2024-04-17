package ee.taltech.iti0202.texteditor.textformatter;

public class CamelCaseFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        String[] words = text.replaceAll("[^\\w\\s]|_", "").split("\\s+");
        StringBuilder camelCaseString = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                camelCaseString.append(words[i].toLowerCase());
            } else {
                camelCaseString.append(words[i].substring(0, 1).toUpperCase())
                        .append(words[i].substring(1).toLowerCase());
            }
        }
        return camelCaseString.toString();
    }
}
