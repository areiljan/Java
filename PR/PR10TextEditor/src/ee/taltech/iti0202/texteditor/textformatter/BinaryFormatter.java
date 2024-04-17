package ee.taltech.iti0202.texteditor.textformatter;

public class BinaryFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        StringBuilder binaryString = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String binary = Integer.toBinaryString(c);

            while (binary.length() < 8) {
                binary = "0" + binary;
            }

            binaryString.append(binary);

            if (i < text.length() - 1 && !(c == '\n' && text.charAt(i + 1) != '\r')) {
                binaryString.append(" ");
            }
        }
        return binaryString.toString();
    }
}
