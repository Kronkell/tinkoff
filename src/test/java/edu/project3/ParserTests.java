package edu.project3;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParserTests {
    @Test
    public void logParserTest() {
        final List<String> rawLogs = List.of("93.180.71.3 - - [17/May/2015:08:05:32 +0000] " +
            "\"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"");

        final List<LogRecord> expectedParsedLogs = List.of(
            new LogRecord(
                "93.180.71.3",
                "-",
                "17/May/2015:08:05:32 +0000",
                "GET /downloads/product_1 HTTP/1.1",
                "304",
                "0",
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ));

        var actualParsedLogs = LogParser.formListOfLogs(rawLogs);

        assertThat(actualParsedLogs).isEqualTo(expectedParsedLogs);
    }
}
