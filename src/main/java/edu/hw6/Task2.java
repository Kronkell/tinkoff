package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Task2 {
    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        long countOfClones;
        String fileNameWithoutExt = path.getFileName().toString().replaceFirst("[.][^.]+$", "");
        try (Stream<Path> foundFiles = Files.walk(path.toAbsolutePath().getParent())) {
            Pattern pattern = Pattern.compile(
                Pattern.quote(fileNameWithoutExt) + " - копия( \\(\\d+\\))?\\.txt$");

            countOfClones = foundFiles
                .filter(Files::isRegularFile)
                .filter(file -> pattern.matcher(file.getFileName().toString()).matches())
                .count();
        }

        String newName;
        if (countOfClones == 0) {
            newName = fileNameWithoutExt + " - копия.txt";
        } else {
            newName = fileNameWithoutExt + " - копия (" + (countOfClones + 1) + ").txt";
        }

        Files.copy(path, Path.of((path.toAbsolutePath().getParent().toString() + "\\" + newName)));
    }
}
