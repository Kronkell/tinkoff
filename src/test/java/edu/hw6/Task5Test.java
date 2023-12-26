package edu.hw6;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task5.HackerNews.hackerNewsTopStories;
import static edu.hw6.Task5.HackerNews.news;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    public void shouldReturnTitleGivenId() {
        final long id = 38309611;
        final String expectedTitle = "OpenAI's board has fired Sam Altman";

        String actualTitle = news(id);

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }
}
