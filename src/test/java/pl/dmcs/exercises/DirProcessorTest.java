package pl.dmcs.exercises;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DirProcessorTest {

    private final Path resourceDirectory = Paths.get("src","test","resources");

    @Test
    void testNumberOfCharactersInPhysicalDirectory() {
        DirProcessor dirProcessor = new DirProcessor();
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        FileProcessor fileProcessor = mock(FileProcessor.class);
        setBehaviorForFileProcessor(fileProcessor, absolutePath + "/dir/a.txt", 50);
        setBehaviorForFileProcessor(fileProcessor, absolutePath + "/dir/b.txt", 20);
        setBehaviorForFileProcessor(fileProcessor, absolutePath + "/dir/c.doc", 300);
        dirProcessor.setFileProcessor(fileProcessor);
        assertEquals(70, dirProcessor.getNumberOfCharactersInAllFilesInDirectory(absolutePath + "/dir"));
    }

    @Test
    void testNumberOfCharactersInDirectory(@TempDir Path tempDir) throws IOException {
        DirProcessor dirProcessor = new DirProcessor();
        Path file = tempDir.resolve("a.txt");
        Path file2 = tempDir.resolve("b.txt");
        Files.write(file, "".getBytes());
        Files.write(file2, "".getBytes());
        FileProcessor fileProcessor = mock(FileProcessor.class);
        setBehaviorForFileProcessor(fileProcessor, file.toString(), 200);
        setBehaviorForFileProcessor(fileProcessor, file2.toString(), 300);
        dirProcessor.setFileProcessor(fileProcessor);
        assertEquals(500, dirProcessor.getNumberOfCharactersInAllFilesInDirectory(tempDir.toString()));
    }

    private void setBehaviorForFileProcessor(FileProcessor fileProcessor, String filePath, int returnValue) {
        when(fileProcessor.getNumberOfCharactersInFile(filePath)).thenReturn(returnValue);
    }

}
