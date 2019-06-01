package pl.dmcs.exercises;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void useTmpFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("a.txt");
        Files.write(file, "hello".getBytes());
        assertEquals(asList("hello"), Files.readAllLines(file));
    }

}
