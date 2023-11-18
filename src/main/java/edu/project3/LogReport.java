package edu.project3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogReport {
    private final String FILES = "Files";
    private final String START_DATE = "Start date";
    private final String END_DATE = "End date";
    private final String REQUEST_COUNT = "Request count";
    private final String AVG_RESPONSE_SIZE = "Average response size";

    private final Resources resources;
    private final StatusCodes statusCodes;
    private final GeneralInfo generalInfo;

    public LogReport(List<LogRecord> logRecordList) {
        resources = new Resources(fetchResources(logRecordList));
        statusCodes = new StatusCodes(fetchStatusCodes(logRecordList));
        generalInfo = new GeneralInfo(fetchGeneralInfo(logRecordList));
    }

    private Map<String, String> fetchGeneralInfo(List<LogRecord> logRecordList) {
        Map<String, String> generalInfoMap = new HashMap<>();

        for (var logRecord : logRecordList) {

        }
        generalInfoMap.put(REQUEST_COUNT, String.valueOf(logRecordList.size()));
        long totalBodySize = logRecordList.stream()
            .map(logRecord -> Integer.parseInt(logRecord.bodyBytesSent()))
            .reduce(0, Integer::sum);

        long avgBodySize = totalBodySize / logRecordList.size();
        generalInfoMap.put(AVG_RESPONSE_SIZE, String.valueOf(avgBodySize) + "b");

        return generalInfoMap;
    }

    private Map<String, Integer> fetchResources(List<LogRecord> logRecordList) {
        Map<String, Integer> resourcesMap = new HashMap<>();

        for (var logRecord : logRecordList) {
            resourcesMap.merge(logRecord.request(), 1, (k, v) -> v + 1);
        }

        return resourcesMap;
    }

    private Map<Integer, Integer> fetchStatusCodes(List<LogRecord> logRecordList) {
        Map<Integer, Integer> statusCodeMap = new HashMap<>();

        for (var logRecord : logRecordList) {
            statusCodeMap.merge(Integer.valueOf(logRecord.status()), 1, (k, v) -> v + 1);
        }

        return statusCodeMap;
    }

    public Resources getResources() {
        return resources;
    }

    public StatusCodes getStatusCodes() {
        return statusCodes;
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }
}
