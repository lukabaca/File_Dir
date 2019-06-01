package pl.dmcs.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {
    public int getNumberOfCharactersInFile(Path filePath) {
        try {
            String data = new String(Files.readAllBytes(filePath));
            return data.length();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
