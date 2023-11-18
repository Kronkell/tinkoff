//package edu.hw6.Task3;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.function.Predicate;
//import org.jetbrains.annotations.NotNull;
//
//public class writable implements Predicate<Path>, AbstractFilter {
//    @Override
//    public boolean accept(Path entry) throws IOException {
//        return false;
//    }
//
//    @Override
//    public boolean test(Path path) {
//        return false;
//    }
//
//    @NotNull
//    @Override
//    public Predicate<Path> and(@NotNull Predicate<? super Path> other) {
//        return Predicate.super.and(other);
//    }
//}
