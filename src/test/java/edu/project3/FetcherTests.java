package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FetcherTests {
    @Test
    public void generalInfoFetcherTest() {
        final String REQUEST_COUNT = "Request count";
        final String AVG_RESPONSE_SIZE = "Average response size";
        final List<LogRecord> logs = List.of(
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:32 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ),
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:23 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            )
        );

        String expectedRequestCount = String.valueOf(2);
        String expectedAvgSize = "0b";

        assertThat(LogReport.fetchGeneralInfo(logs, List.of(), OffsetDateTime.MIN, OffsetDateTime.MAX)
            .get(REQUEST_COUNT))
            .isEqualTo(expectedRequestCount);
        assertThat(LogReport.fetchGeneralInfo(logs, List.of(), OffsetDateTime.MIN, OffsetDateTime.MAX)
            .get(AVG_RESPONSE_SIZE))
            .isEqualTo(expectedAvgSize);
    }

    @Test
    public void resourcesFetcherTest() {
        final List<LogRecord> logs = List.of(
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:32 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ),
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:23 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            )
        );

        Integer expectedResourceCount = 2;

        assertThat(LogReport.fetchResources(logs, OffsetDateTime.MIN, OffsetDateTime.MAX)
            .get("GET /downloads/product_1 HTTP/1.1"))
            .isEqualTo(expectedResourceCount);
    }

    @Test
    public void statusCodesFetcherTest() {
        final List<LogRecord> logs = List.of(
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:32 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ),
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:23 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            )
        );

        Integer expectedStatusCodeCount = 2;

        assertThat(LogReport.fetchStatusCodes(logs, OffsetDateTime.MIN, OffsetDateTime.MAX)
            .get(304))
            .isEqualTo(expectedStatusCodeCount);
    }

    @Test
    public void remoteAddrsFetcherTest() {
        final List<LogRecord> logs = List.of(
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:32 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ),
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:23 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            )
        );

        Integer expectedRemoteAddrsCount = 2;

        assertThat(LogReport.fetchRemoteAddrs(logs, OffsetDateTime.MIN, OffsetDateTime.MAX)
            .get("93.180.71.3"))
            .isEqualTo(expectedRemoteAddrsCount);
    }

    @Test
    public void userAgentsFetcherTest() {
        final List<LogRecord> logs = List.of(
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:32 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ),
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:23 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            )
        );

        Integer expectedUserAgentsCount = 2;

        assertThat(LogReport.fetchUserAgents(logs, OffsetDateTime.MIN, OffsetDateTime.MAX)
            .get("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"))
            .isEqualTo(expectedUserAgentsCount);
    }
}
