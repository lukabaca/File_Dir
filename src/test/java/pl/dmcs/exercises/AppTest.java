package pl.dmcs.exercises;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppTest {
    @Rule
    public static TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void testNumberOfCharactersInFile(@TempDir Path tempDir) {
        checkNumberOfCharactersInFile(tempDir, 0, "");
        checkNumberOfCharactersInFile(tempDir, 5, "hello");
        checkNumberOfCharactersInFile(tempDir, 1, "a");
        checkNumberOfCharactersInFile(tempDir, 4, "1234");
    }

    private void checkNumberOfCharactersInFile(@TempDir Path tempDir, int expectedNumberOfCharacters, String fileContent)  {
        try {
            FileProcessor fileProcessor = new FileProcessor();
            Path file = tempDir.resolve("a.txt");
            Files.write(file, fileContent.getBytes());
            assertEquals(expectedNumberOfCharacters, fileProcessor.getNumberOfCharactersInFile(file.toString()));
        } catch (IOException e) {

        }
    }

    @Test
    public void mokitoTest(@TempDir Path tempDir) {
        Path file = tempDir.resolve("a.txt");
        FileProcessor fileProcessor = mock(FileProcessor.class);
        when(fileProcessor.getNumberOfCharactersInFile(file.toString())).thenReturn(5);
        assertEquals(fileProcessor.getNumberOfCharactersInFile(file.toString()), 5);
    }

    @Test
    public void tmpDirTest(@TempDir Path tempDir) throws IOException {
        DirProcessor dirProcessor = new DirProcessor();
        Path file = tempDir.resolve("a.txt");
        Path file2 = tempDir.resolve("b.txt");
        Files.write(file, "a".getBytes());
        Files.write(file2, "bb".getBytes());
        assertEquals(3, dirProcessor.getNumberOfCharactersInAllFilesInDirectory(tempDir.toString()));
    }
}
