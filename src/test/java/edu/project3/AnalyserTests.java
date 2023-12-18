package edu.project3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnalyserTests {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void outputTest() throws ParseException, IOException {
        final String[] args = {
            "--path",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"
        };
        final String expectedOutput = """
            #### General Info

            |*METRIC*              |                                                                                                 *VALUE*|
            |----------------------|--------------------------------------------------------------------------------------------------------|
            |Files                 | https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs|
            |Start date            |                                                                                                      - |
            |End date              |                                                                                                      - |
            |Request count         |                                                                                                   51462|
            |Average response size |                                                                                                 659509b|

            #### Resources

            |*RESOURCE*                         | *COUNT*|
            |-----------------------------------|--------|
            |GET /downloads/product_1 HTTP/1.1  |   30272|
            |GET /downloads/product_2 HTTP/1.1  |   21034|
            |GET /downloads/product_3 HTTP/1.1  |      73|
            |HEAD /downloads/product_2 HTTP/1.1 |      70|
            |HEAD /downloads/product_1 HTTP/1.1 |      13|

            #### Status codes

            |*STATUS CODE*|                  *DESCRIPTION*|*COUNT*|
            |-------------|-------------------------------|-------|
            |404          |                      Not Found|  33876|
            |304          |                   Not Modified|  13330|
            |200          |                             OK|   4028|
            |206          |                Partial Content|    186|
            |403          |                      Forbidden|     38|
            |416          |Requested Range Not Satisfiable|      4|

            #### Remote addresses

            |*REMOTE ADDRESS* | *COUNT*|
            |-----------------|--------|
            |216.46.173.126   |    2350|
            |180.179.174.219  |    1720|
            |204.77.168.241   |    1439|
            |65.39.197.164    |    1365|
            |80.91.33.133     |    1202|
            |84.208.15.12     |    1120|
            |74.125.60.158    |    1084|
            |119.252.76.162   |    1064|
            |79.136.114.202   |     628|
            |54.207.57.55     |     532|

            #### User agents

            |*USER AGENT*                                  | *COUNT*|
            |----------------------------------------------|--------|
            |Debian APT-HTTP/1.3 (1.0.1ubuntu2)            |   11830|
            |Debian APT-HTTP/1.3 (0.9.7.9)                 |   11365|
            |Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21) |    6719|
            |Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16) |    5740|
            |Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22) |    3855|
            |Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17) |    1827|
            |Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.7)  |    1255|
            |urlgrabber/3.9.1 yum/3.2.29                   |     792|
            |Debian APT-HTTP/1.3 (0.9.7.8)                 |     750|
            |urlgrabber/3.9.1 yum/3.4.3                    |     708|""";

        LogAnalyser.run(args);

        assertThat(outContent.toString().trim().replace("\r", "")).isEqualTo(expectedOutput.trim());
    }

    @AfterAll
    public static void restoreStream() {
        System.setOut(originalOut);
    }
}
