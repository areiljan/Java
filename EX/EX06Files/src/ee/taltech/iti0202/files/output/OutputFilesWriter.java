package ee.taltech.iti0202.files.output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OutputFilesWriter {
    public boolean writeLinesToFile(List<String> lines, String filename) {
        Path path = Paths.get(filename);
        boolean fileExists = Files.exists(path);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines) {
                writer.write(line);
            }
            return true;
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}