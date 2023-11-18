//package edu.hw6.Task3;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.function.Predicate;
//import org.jetbrains.annotations.NotNull;
//
//public class largerThan implements Predicate<long>, AbstractFilter {
//    @Override
//    public boolean accept(Path entry) throws IOException {
//        return false;
//    }
//=
//    @Override
//    public boolean test(long l) {
//        return false;
//    }
//
//    @NotNull
//    @Override
//    public Predicate<long> and(@NotNull Predicate<? super Path> other) {
//        return Predicate.super.and(other);
//    }
//}
