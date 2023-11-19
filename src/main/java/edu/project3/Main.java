package edu.project3;

import java.io.IOException;
import org.apache.commons.cli.ParseException;

public class Main {
    private Main() {
    }

    public static void main(String[] args) throws ParseException, IOException {
        LogAnalyser.run(args);
    }
}
