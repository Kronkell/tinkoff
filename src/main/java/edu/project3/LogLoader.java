package edu.project3;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogLoader {
    private LogLoader() {
    }

    public static List<String> loadLogsFromFile(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    public static List<String> loadLogsFromURL(URL url) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest
                .newBuilder()
                .uri(url.toURI())
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return Arrays.stream(response.body().split("\n"))
                .collect(Collectors.toList());

        } catch (Exception e) {
            return List.of();
        }
    }

}
