package helpers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Helpers {
    public static List<String> readAllLines(Path resoursePath) throws IOException {
        return Files.readAllLines(resoursePath, Charset.defaultCharset());
    }
}
