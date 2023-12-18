package edu.project3;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RenderTests {
    @Test
    public void generalInfoRendererTest() {
        final Map<String, String> infoMap = new LinkedHashMap<>();
        infoMap.put("Files", "info.txt");
        infoMap.put("Start date", "2015-06-04");
        infoMap.put("End date", "2015-06-05");
        infoMap.put("Request count", "896");
        infoMap.put("Average response size", "798510b");

        String expectedOutput = "|*METRIC*              |    *VALUE*|\n" +
            "|----------------------|-----------|\n" +
            "|Files                 |   info.txt|\n" +
            "|Start date            | 2015-06-04|\n" +
            "|End date              | 2015-06-05|\n" +
            "|Request count         |        896|\n" +
            "|Average response size |    798510b|\n";

        String actualOutput = DefaultRenderer.renderGeneralInfo(new GeneralInfo(infoMap), false);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void resourcesRendererTest() {
        final Map<String, Integer> infoMap = new LinkedHashMap<>();
        infoMap.put("GET /downloads/product_1 HTTP/1.1", 566);
        infoMap.put("GET /downloads/product_2 HTTP/1.1", 327);
        infoMap.put("GET /downloads/product_3 HTTP/1.1", 2);
        infoMap.put("HEAD /downloads/product_2 HTTP/1.1", 1);

        String expectedOutput = "|*RESOURCE*                         | *COUNT*|\n" +
            "|-----------------------------------|--------|\n" +
            "|GET /downloads/product_1 HTTP/1.1  |     566|\n" +
            "|GET /downloads/product_2 HTTP/1.1  |     327|\n" +
            "|GET /downloads/product_3 HTTP/1.1  |       2|\n" +
            "|HEAD /downloads/product_2 HTTP/1.1 |       1|\n";

        String actualOutput = DefaultRenderer.renderResources(new Resources(infoMap), false);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void statusCodesRendererTest() {
        final Map<Integer, Integer> infoMap = new LinkedHashMap<>();
        infoMap.put(404, 564);
        infoMap.put(304, 258);
        infoMap.put(200, 71);
        infoMap.put(206, 2);
        infoMap.put(403, 1);

        String expectedOutput = "|*STATUS CODE*|  *DESCRIPTION*|*COUNT*|\n" +
            "|-------------|---------------|-------|\n" +
            "|404          |      Not Found|    564|\n" +
            "|304          |   Not Modified|    258|\n" +
            "|200          |             OK|     71|\n" +
            "|206          |Partial Content|      2|\n" +
            "|403          |      Forbidden|      1|\n";

        String actualOutput = DefaultRenderer.renderStatusCodes(new StatusCodes(infoMap), false);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void remoteAddrsRendererTest() {
        final Map<String, Integer> infoMap = new LinkedHashMap<>();
        infoMap.put("119.252.76.162", 50);
        infoMap.put("216.46.173.126", 45);
        infoMap.put("84.208.15.12", 30);
        infoMap.put("192.73.242.182", 30);

        String expectedOutput = "|*REMOTE ADDRESS* | *COUNT*|\n" +
            "|-----------------|--------|\n" +
            "|119.252.76.162   |      50|\n" +
            "|216.46.173.126   |      45|\n" +
            "|84.208.15.12     |      30|\n" +
            "|192.73.242.182   |      30|\n";

        String actualOutput = DefaultRenderer.renderRemoteAddrs(new RemoteAddresses(infoMap), false);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void userAgentsRendererTest() {
        final Map<String, Integer> infoMap = new LinkedHashMap<>();
        infoMap.put("Debian APT-HTTP/1.3 (0.9.7.9)", 213);
        infoMap.put("Debian APT-HTTP/1.3 (1.0.1ubuntu2)", 205);
        infoMap.put("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", 179);
        infoMap.put("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)", 67);

        String expectedOutput = "|*USER AGENT*                                  | *COUNT*|\n" +
            "|----------------------------------------------|--------|\n" +
            "|Debian APT-HTTP/1.3 (0.9.7.9)                 |     213|\n" +
            "|Debian APT-HTTP/1.3 (1.0.1ubuntu2)            |     205|\n" +
            "|Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21) |     179|\n" +
            "|Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22) |      67|\n";

        String actualOutput = DefaultRenderer.renderUserAgents(new UserAgents(infoMap), false);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

}
