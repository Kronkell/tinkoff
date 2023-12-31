package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Pattern;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
public class HackerNews {
    private HackerNews() {
    }

    private static final String URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final String TITLE_REGEXP = "\"title\":\"([^\"]*)\"";

    public static long[] hackerNewsTopStories() {
        String topStoriesUrl = "https://hacker-news.firebaseio.com/v0/topstories.json";

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(topStoriesUrl))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return Arrays.stream(response.body().substring(1, response.body().length() - 1)
                    .split(","))
                .map(Long::parseLong)
                .mapToLong(Long::longValue)
                .toArray();
        } catch (Exception e) {
            return new long[0];
        }
    }

    public static String news(long id) {
        String newsURL = String.format(URL, id);
        String title = "";

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(newsURL))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Pattern pattern = Pattern.compile(TITLE_REGEXP);

            var matcher = pattern.matcher(response.body());
            if (matcher.find()) {
                title = matcher.group(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return title;
    }

}

