import ee.taltech.iti0202.api.SchoolDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class TestTest {

    @Test
    public void testFileLoader() throws IOException {
        String filePath = "C:\\Users\\Kasutaja\\IdeaProjects\\iti0202-2024\\PR\\PR14Api\\src\\ee\\taltech\\iti0202\\api\\Test.json";
        String jsonContent = new String(readAllBytes(Paths.get(filePath)));
        SchoolDatabase schoolDatabase = new SchoolDatabase(jsonContent);
    }
}