package edu.project3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private LogParser() {
    }

    private static final String LOG_REGEX =
        "^([^ ]*) - ([^ ]*) \\[([^\\]]*)\\] \"([^\"]*)\" ([0-9]*) ([0-9]*) \"([^\"]*)\" \"([^\"]*)\"$";
    private static final int REMOTE_ADD = 1;
    private static final int REMOTE_USER = 2;
    private static final int TIME_LOCAL = 3;
    private static final int REQUEST = 4;
    private static final int STATUS = 5;
    private static final int BODY_BYTES_SENT = 6;
    private static final int HTTP_REFERER = 7;
    private static final int HTTP_USER_AGENT = 8;

    public static List<LogRecord> formListOfLogs(List<String> rawLogs) {
        ArrayList<LogRecord> logRecords = new ArrayList<>();
        Pattern pattern = Pattern.compile(LOG_REGEX);
        for (var line : rawLogs) {
            Matcher m = pattern.matcher(line);
            while (m.find()) {
                logRecords.add(
                    new LogRecord(
                        m.group(REMOTE_ADD),
                        m.group(REMOTE_USER),
                        m.group(TIME_LOCAL),
                        m.group(REQUEST),
                        m.group(STATUS),
                        m.group(BODY_BYTES_SENT),
                        m.group(HTTP_REFERER),
                        m.group(HTTP_USER_AGENT)
                    ));
            }
        }

        return logRecords;
    }
}
