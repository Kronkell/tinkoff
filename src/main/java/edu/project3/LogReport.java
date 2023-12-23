package edu.project3;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.project3.StringConsts.AVG_RESPONSE_SIZE;
import static edu.project3.StringConsts.DASH;
import static edu.project3.StringConsts.END_DATE;
import static edu.project3.StringConsts.FILES;
import static edu.project3.StringConsts.REQUEST_COUNT;
import static edu.project3.StringConsts.START_DATE;

public class LogReport {
    private static final int MAX_OUTPUT_SIZE = 10;

    private final Resources resources;
    private final StatusCodes statusCodes;
    private final GeneralInfo generalInfo;
    private final RemoteAddresses remoteAddresses;
    private final UserAgents userAgents;
    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private final List<String> sources;

    public LogReport(List<LogRecord> logRecordList, List<String> loadSources, String fromDate, String toDate) {
        if (fromDate == null && toDate == null) {
            from = OffsetDateTime.MIN;
            to = OffsetDateTime.MAX;
        } else if (fromDate == null) {
            from = OffsetDateTime.MIN;
            to = OffsetDateTime.from(LocalDate.parse(toDate).atStartOfDay(ZoneOffset.UTC));
        } else if (toDate == null) {
            from = OffsetDateTime.from(LocalDate.parse(fromDate).atStartOfDay(ZoneOffset.UTC));
            to = OffsetDateTime.MAX;
        } else {
            from = OffsetDateTime.from(LocalDate.parse(fromDate).atStartOfDay(ZoneOffset.UTC));
            to = OffsetDateTime.from(LocalDate.parse(toDate).atStartOfDay(ZoneOffset.UTC));
        }
        sources = loadSources;

        resources = new Resources(fetchResources(logRecordList, from, to));
        statusCodes = new StatusCodes(fetchStatusCodes(logRecordList, from, to));
        generalInfo = new GeneralInfo(fetchGeneralInfo(logRecordList, sources, from, to));
        remoteAddresses = new RemoteAddresses(fetchRemoteAddrs(logRecordList, from, to));
        userAgents = new UserAgents(fetchUserAgents(logRecordList, from, to));
    }

    public static Map<String, String> fetchGeneralInfo(
        List<LogRecord> logRecordList,
        List<String> sources,
        OffsetDateTime from,
        OffsetDateTime to
    ) {
        Map<String, String> generalInfoMap = new LinkedHashMap<>();
        long totalBodySize = 0;
        long requestCount = 0;

        for (var logRecord : logRecordList) {
            OffsetDateTime time = parseStringDate(logRecord.timeLocal());

            if (time.isAfter(from) && time.isBefore(to)) {
                totalBodySize += Long.parseLong(logRecord.bodyBytesSent());
                requestCount++;
            }
        }

        generalInfoMap.put(FILES, sources.toString().substring(1, sources.toString().length() - 1));
        if (from.isEqual(OffsetDateTime.MIN)) {
            generalInfoMap.put(START_DATE, DASH);
        } else {
            generalInfoMap.put(START_DATE, String.valueOf(LocalDate.from(from)));
        }
        if (to.isEqual(OffsetDateTime.MAX)) {
            generalInfoMap.put(END_DATE, DASH);
        } else {
            generalInfoMap.put(END_DATE, String.valueOf(LocalDate.from(to)));
        }
        generalInfoMap.put(REQUEST_COUNT, String.valueOf(requestCount));

        if (requestCount > 0) {
            long avgBodySize = totalBodySize / requestCount;
            generalInfoMap.put(AVG_RESPONSE_SIZE, avgBodySize + "b");
        } else {
            generalInfoMap.put(AVG_RESPONSE_SIZE, DASH);
        }

        return generalInfoMap;
    }

    public static Map<String, Integer> fetchResources(
        List<LogRecord> logRecordList,
        OffsetDateTime from,
        OffsetDateTime to
    ) {
        Map<String, Integer> resourcesMap = new LinkedHashMap<>();

        for (var logRecord : logRecordList) {
            OffsetDateTime time = parseStringDate(logRecord.timeLocal());

            if (time.isAfter(from) && time.isBefore(to)) {
                resourcesMap.merge(logRecord.request(), 1, (k, v) -> k + 1);
            }
        }

        return resourcesMap
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(MAX_OUTPUT_SIZE)
            .collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static Map<Integer, Integer> fetchStatusCodes(
        List<LogRecord> logRecordList,
        OffsetDateTime from,
        OffsetDateTime to
    ) {
        Map<Integer, Integer> statusCodeMap = new LinkedHashMap<>();

        for (var logRecord : logRecordList) {
            OffsetDateTime time = parseStringDate(logRecord.timeLocal());

            if (time.isAfter(from) && time.isBefore(to)) {
                statusCodeMap.merge(Integer.valueOf(logRecord.status()), 1, (k, v) -> k + 1);
            }
        }

        return statusCodeMap
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(MAX_OUTPUT_SIZE)
            .collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static Map<String, Integer> fetchRemoteAddrs(
        List<LogRecord> logRecordList,
        OffsetDateTime from,
        OffsetDateTime to
    ) {
        Map<String, Integer> remoteAddrsMap = new LinkedHashMap<>();

        for (var logRecord : logRecordList) {
            OffsetDateTime time = parseStringDate(logRecord.timeLocal());

            if (time.isAfter(from) && time.isBefore(to)) {
                remoteAddrsMap.merge(logRecord.remoteAddr(), 1, (k, v) -> k + 1);
            }
        }

        return remoteAddrsMap
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(MAX_OUTPUT_SIZE)
            .collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static Map<String, Integer> fetchUserAgents(
        List<LogRecord> logRecordList,
        OffsetDateTime from,
        OffsetDateTime to
    ) {
        Map<String, Integer> userAgentsMap = new LinkedHashMap<>();

        for (var logRecord : logRecordList) {
            OffsetDateTime time = parseStringDate(logRecord.timeLocal());

            if (time.isAfter(from) && time.isBefore(to)) {
                userAgentsMap.merge(logRecord.httpUserAgent(), 1, (k, v) -> k + 1);
            }
        }

        return userAgentsMap
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(MAX_OUTPUT_SIZE)
            .collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static OffsetDateTime parseStringDate(String date) {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
            .appendPattern("dd/MMM/yyyy:HH:mm:ss ")
            .appendOffset("+HHMM", "Z")
            .toFormatter(Locale.ENGLISH);

        return OffsetDateTime.parse(date, dtf);
    }

    public Info getInfo() {
        return new Info(resources, statusCodes, generalInfo, remoteAddresses, userAgents);
    }
}
