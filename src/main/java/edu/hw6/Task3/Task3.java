//package edu.hw6.Task3;
//
//import java.io.FileFilter;
//import java.io.IOException;
//import java.nio.file.DirectoryStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.function.Predicate;
//import static edu.hw6.Task3.AbstractFilter.titleFilter1;
//import static edu.hw6.Task3.AbstractFilter.titleFilter2;
//
//public class Task3 {
//    public static final AbstractFilter regularFile = Files::isRegularFile;
//    public static final largerThan largerThan = new largerThan();
//    DirectoryStream.Filter<Path> pathFilter = new DirectoryStream.Filter<Path>()
//    {
//        @Override
//        public boolean accept(Path entry) throws IOException
//        {
//            return false;
//        }
//    };
//    DirectoryStream.Filter<Path> filter = titleFilter1("KEKW")
//        .and(titleFilter1("KEKW"))
//        .and(titleFilter2("LULW"));
//    DirectoryStream.Filter<Path> filter1= regularFile
//        .and(largerThan(100_000))
//        .and(magicNumber(0x89, 'P', 'N', 'G'))
//
//    try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("kekw.txt"), filter)) {
//        entries.forEach(System.out::println);
//    }
//
//    public Task3() throws IOException {
//    }
//}
