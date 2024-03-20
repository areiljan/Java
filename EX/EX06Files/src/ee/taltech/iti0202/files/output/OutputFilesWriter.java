package ee.taltech.iti0202.files.output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OutputFilesWriter {
    /**
     * Write lines to file using the BufferedWriter.
     * @param lines - which lines to write.
     * @param filename - the filename.
     * @return - true, if succeeded.
     */
    public boolean writeLinesToFile(List<String> lines, String filename) {
        Path path = Paths.get(filename);
        boolean fileExists = Files.exists(path);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
