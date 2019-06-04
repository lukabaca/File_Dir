package pl.dmcs.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {

    public int getNumberOfCharactersInFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            String data = new String(Files.readAllBytes(path));
            return data.length();
        } catch (IOException e) {
            return -1;
        }
    }
}
