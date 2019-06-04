package pl.dmcs.exercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileProcessorTest {

    @Test
    void testNumberOfCharactersInFile(@TempDir Path tempDir) {
        checkNumberOfCharactersInFile(tempDir, 0, "");
        checkNumberOfCharactersInFile(tempDir, 5, "hello");
        checkNumberOfCharactersInFile(tempDir, 1, "a");
        checkNumberOfCharactersInFile(tempDir, 4, "1234");
    }

    private void checkNumberOfCharactersInFile(@TempDir Path tempDir, int expectedNumberOfCharacters, String fileContent) {
        try {
            FileProcessor fileProcessor = new FileProcessor();
            Path file = tempDir.resolve("a.txt");
            Files.write(file, fileContent.getBytes());
            assertEquals(expectedNumberOfCharacters, fileProcessor.getNumberOfCharactersInFile(file.toString()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
