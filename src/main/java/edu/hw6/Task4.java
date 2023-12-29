package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

@SuppressWarnings("UncommentedMain")
public class Task4 {
    private Task4() {
    }

    public static void main(String[] args) {
        streamsComposition();
    }

    public static void streamsComposition() {
        try (PrintWriter writer =
                 new PrintWriter(
                     new OutputStreamWriter(
                         new BufferedOutputStream(
                             new CheckedOutputStream(
                                 Files.newOutputStream(Path.of("src/main/resources/hw6/chain.txt")), new CRC32()
                             )
                         ), StandardCharsets.UTF_8)
                 )
        ) {

            String message = "Programming is learned by writing programs. ― Brian Kernighan";
            writer.print(message);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
