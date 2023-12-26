package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static edu.hw6.Task3.Filters.extensionIs;
import static edu.hw6.Task3.Filters.largerThan;
import static edu.hw6.Task3.Filters.magicNumber;
import static edu.hw6.Task3.Filters.matchesPattern;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static DirectoryStream.Filter<Path> filter = REGULAR_FILE
        .and(READABLE)
        .and(largerThan(10000))
        .and(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
        .and(extensionIs("png"))
        .and(matchesPattern(Pattern.compile(".*_.*")));

    @Test
    public void shouldCorrectlyFilterFiles() {
        Path pathFile = Paths.get("src/main/resources/hw6");
        List<String> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(pathFile, filter)) {
            entries.forEach(path -> paths.add(path.toString()));
            assertThat(paths).containsExactly("src/main/resources/hw6/__photo.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
