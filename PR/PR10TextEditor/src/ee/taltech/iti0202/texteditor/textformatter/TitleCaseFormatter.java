package ee.taltech.iti0202.texteditor.textformatter;

import java.util.ArrayList;

public class TitleCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        String[] words = text.split(" ");
        String newText = "";
        ArrayList<String> listOfWords = new ArrayList<>();
        listOfWords.add("a");
        listOfWords.add("an");
        listOfWords.add("of");
        listOfWords.add("the");
        listOfWords.add("and");
        listOfWords.add("or");
        for (int i = 0; i < words.length; i++) {
            if (i == 0 || i == words.length) {
                newText = newText + " " + words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            } else if (listOfWords.contains(words[i])) {
                newText = newText + " " + words[i].toLowerCase();
            } else {
                newText = newText + words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            }
        }
        newText += "\n";
        return newText;
    }
}
