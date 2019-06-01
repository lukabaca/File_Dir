package pl.dmcs.exercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppTest {

    @Test
    void testNumberOfCharactersInFile(@TempDir Path tempDir) {
        checkNumberOfCharactersInFile(tempDir, 0, "");
        checkNumberOfCharactersInFile(tempDir, 5, "hello");
        checkNumberOfCharactersInFile(tempDir, 1, "a");
        checkNumberOfCharactersInFile(tempDir, 4, "1234");
    }

    @Test
    void testNumberOfCharactersInDirectoryMocked(@TempDir Path tempDir) {
        DirProcessor dirProcessor = mock(DirProcessor.class);
        when(dirProcessor.getNumberOfCharactersInAllFilesInDirectory(tempDir.toString())).thenReturn(5);
        assertEquals(dirProcessor.getNumberOfCharactersInAllFilesInDirectory(tempDir.toString()), 5);
    }

    @Test
    void testNumberOfCharactersInDirectory(@TempDir Path tempDir) throws IOException {
        DirProcessor dirProcessor = new DirProcessor();
        Path file = tempDir.resolve("a.txt");
        Path file2 = tempDir.resolve("b.txt");
        Path file3 = tempDir.resolve("c.pdf");
        Files.write(file, "a".getBytes());
        Files.write(file2, "bb".getBytes());
        assertEquals(3, dirProcessor.getNumberOfCharactersInAllFilesInDirectory(tempDir.toString()));
    }

    @Test
    void testNumberOfCharactersInDirectoryPhysicalPath() {
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        checkNumberOfCharactersInDirectoryPhysicalPath(4, absolutePath + "/dir");
        checkNumberOfCharactersInDirectoryPhysicalPath(5, absolutePath + "/dir2");
    }

    private void checkNumberOfCharactersInDirectoryPhysicalPath(int expectedNumberOfCharacters, String directoryPath) {
        DirProcessor dirProcessor = new DirProcessor();
        assertEquals(expectedNumberOfCharacters, dirProcessor.getNumberOfCharactersInAllFilesInDirectory(directoryPath));
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
