package ee.taltech.iti0202.texteditor.textformatter;

public class BinaryFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null) {
            // Handle null input gracefully
            return "";
        }

        StringBuilder binaryString = new StringBuilder();
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            char currentChar = characters[i];
            if (i == characters.length - 1 && currentChar == '\n') {
                binaryString.append("\n");
                break;
            }
            String binaryLetter = Integer.toBinaryString(characters[i]);
            while (binaryLetter.length() < 8) {
                binaryLetter = "0" + binaryLetter;
            }
            binaryString.append(binaryLetter);
        }
        return binaryString.toString();
    }
}
