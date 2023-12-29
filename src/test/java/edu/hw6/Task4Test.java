package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task4.streamsComposition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    private final Path filePath = Paths.get("src/main/resources/hw6/chain.txt");
    private final String line = "Programming is learned by writing programs. ― Brian Kernighan";

    @Test
    public void shouldCreateFile() {
        streamsComposition();
        assertTrue(Files.exists(filePath));
    }

    @Test
    public void shouldWriteLineInCreatedFile() throws IOException {
        streamsComposition();
        assertEquals(line, Files.readAllLines(filePath).get(0));
    }
}
