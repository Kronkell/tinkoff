//package edu.hw6.Task3;
//
//import java.io.IOException;
//import java.nio.file.DirectoryStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Objects;
//import java.util.function.Predicate;
//import org.jetbrains.annotations.NotNull;
//
//public interface AbstractFilter extends DirectoryStream.Filter<Path>{
//
//
//    public boolean test(Path path);
//
//    default DirectoryStream.Filter<Path> and(DirectoryStream.Filter<Path> other) {
//        Objects.requireNonNull(other);
//        return (t) -> test(t) && other.test(t);
//    }
//
//    AbstractFilter largerThan = new AbstractFilter() {
//        @Override
//        public boolean test(Path path) {
//            return false;
//        }
//
//        @Override
//        public boolean accept(Path entry) throws IOException {
//            return false;
//        }
//    };
//
//    AbstractFilter magicNumber = new AbstractFilter() {
//        @Override
//        public boolean test(Path path) {
//            return false;
//        }
//
//        @Override
//        public boolean accept(Path entry) throws IOException {
//            return false;
//        }
//    };
//    public static Predicate<Path> titleFilter(String filter){
//        return m -> m.getFileName().toString().contains(filter.toLowerCase());
//    }
//    public static Predicate<Path> titleFilter1(String filter){
//        return m -> m.getFileName().toString().contains(filter.toLowerCase());
//    }
//    public static Predicate<Path> titleFilter2(String filter){
//        return m -> m.getFileName().toString().contains(filter.toLowerCase());
//    }
//
//}
