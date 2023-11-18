package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

public class readable implements DirectoryStream.Filter<Path>, Predicate<Path>{
    @Override
    public boolean test(Path path) {
        return Files.isRegularFile(path);
    }

    @NotNull
    public Predicate<Path> and(@NotNull Predicate<? super Path> other) {
        return Predicate.super.and(other);
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        return false;
    }
}
