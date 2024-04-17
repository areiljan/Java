package ee.taltech.iti0202.texteditor.textformatter;

public class BinaryFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null) {
            // Handle null input gracefully
            return "";
        }

        StringBuilder binaryString = new StringBuilder();
        for (char c : text.toCharArray()) {
            binaryString.append(Integer.toBinaryString(c)).append(" ");
        }
        return binaryString.toString().trim();
    }
}
