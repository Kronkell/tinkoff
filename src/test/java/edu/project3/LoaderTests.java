package edu.project3;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoaderTests {
    @Test
    public void logLoaderTest() throws MalformedURLException {
        final int logNum = 5;
        final String url = "https://raw.githubusercontent.com/elastic/" +
            "examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

        final String logs = """
                93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                93.180.71.3 - - [17/May/2015:08:05:23 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                80.91.33.133 - - [17/May/2015:08:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
                217.168.17.5 - - [17/May/2015:08:05:34 +0000] "GET /downloads/product_1 HTTP/1.1" 200 490 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
                217.168.17.5 - - [17/May/2015:08:05:09 +0000] "GET /downloads/product_2 HTTP/1.1" 200 490 "-" "Debian APT-HTTP/1.3 (0.8.10.3)\"""";
        final List<String> expectedLogs = List.of(logs.split("\n"));
        List<String> actualLogs = LogLoader.loadLogsFromURL(URI.create(url).toURL()).subList(0, logNum);

        assertThat(actualLogs).isEqualTo(expectedLogs);
    }
}
