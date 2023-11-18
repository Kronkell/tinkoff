package edu.project3;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LogLoader {

    public List<String> loadLogsFromFile(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    public List<String> loadLogsFromURL(URL url) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = client;
    }

}
