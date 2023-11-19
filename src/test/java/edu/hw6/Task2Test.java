package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class Task2Test {
    public static void main(String[] args) throws IOException {
        Task2.cloneFile(Path.of("C:\\Users\\piano\\Test\\c.txt"));
    }

    @Test
    public void firstCopyTest() {
        var path = Path.of("Test.txt");
    }
}
