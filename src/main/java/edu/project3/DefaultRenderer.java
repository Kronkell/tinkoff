package edu.project3;

import java.util.Comparator;
import org.apache.commons.httpclient.HttpStatus;
import static edu.project3.StringConsts.FIRST_COLUMN_HEADER;
import static edu.project3.StringConsts.PIPE;
import static edu.project3.StringConsts.PIPE_NEWLINE;
import static edu.project3.StringConsts.REMOTE_ADDR_FIRST_COLUMN_HEADER;
import static edu.project3.StringConsts.REMOTE_ADDR_SECOND_COLUMN_HEADER;
import static edu.project3.StringConsts.RESOURCES_FIRST_COLUMN_HEADER;
import static edu.project3.StringConsts.RESOURCES_SECOND_COLUMN_HEADER;
import static edu.project3.StringConsts.SECOND_COLUMN_HEADER;
import static edu.project3.StringConsts.STATUS_CODES_FIRST_COLUMN_HEADER;
import static edu.project3.StringConsts.STATUS_CODES_SECOND_COLUMN_HEADER;
import static edu.project3.StringConsts.STATUS_CODES_THIRD_COLUMN_HEADER;
import static edu.project3.StringConsts.USER_AGENTS_FIRST_COLUMN_HEADER;
import static edu.project3.StringConsts.USER_AGENTS_SECOND_COLUMN_HEADER;

public class DefaultRenderer {
    private DefaultRenderer() {
    }

    public static String renderGeneralInfo(GeneralInfo generalInfo, boolean isAdoc) {
        var sb = new StringBuilder();

        int firstColumnWidth = calculateFirstColumnWidth(generalInfo);
        int secondColumnWidth = calculateValuesColumnWidth(generalInfo);

        appendHeader(sb, FIRST_COLUMN_HEADER, SECOND_COLUMN_HEADER, firstColumnWidth, secondColumnWidth, isAdoc);

        populateTable(sb, generalInfo, firstColumnWidth, secondColumnWidth, 0, isAdoc);

        return sb.toString();
    }

    public static String renderResources(Resources resources, boolean isAdoc) {
        if (resources.resourcesMap().isEmpty()) {
            return "";
        }

        var sb = new StringBuilder();

        int firstColumnWidth = calculateFirstColumnWidth(resources);
        int secondColumnWidth = calculateValuesColumnWidth(resources);

        firstColumnWidth = Math.max(firstColumnWidth, RESOURCES_FIRST_COLUMN_HEADER.length());
        secondColumnWidth = Math.max(secondColumnWidth, RESOURCES_SECOND_COLUMN_HEADER.length());

        appendHeader(
            sb,
            RESOURCES_FIRST_COLUMN_HEADER,
            RESOURCES_SECOND_COLUMN_HEADER,
            firstColumnWidth,
            secondColumnWidth,
            isAdoc
        );

        populateTable(sb, resources, firstColumnWidth, secondColumnWidth, 0, isAdoc);

        return sb.toString();
    }

    public static String renderStatusCodes(StatusCodes statusCodes, boolean isAdoc) {
        if (statusCodes.statusCodeMap().isEmpty()) {
            return "";
        }

        var sb = new StringBuilder();

        int firstColumnWidth = STATUS_CODES_FIRST_COLUMN_HEADER.length();
        int secondColumnWidth = statusCodes.statusCodeMap()
            .keySet()
            .stream()
            .map(HttpStatus::getStatusText)
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();
        int thirdColumnWidth = calculateValuesColumnWidth(statusCodes);

        secondColumnWidth = Math.max(secondColumnWidth, STATUS_CODES_SECOND_COLUMN_HEADER.length());
        thirdColumnWidth = Math.max(thirdColumnWidth, STATUS_CODES_THIRD_COLUMN_HEADER.length());

        appendWideHeader(
            sb,
            STATUS_CODES_FIRST_COLUMN_HEADER,
            STATUS_CODES_SECOND_COLUMN_HEADER,
            STATUS_CODES_THIRD_COLUMN_HEADER,
            firstColumnWidth,
            secondColumnWidth,
            thirdColumnWidth,
            isAdoc
        );

        populateTable(sb, statusCodes, firstColumnWidth, secondColumnWidth, thirdColumnWidth, isAdoc);

        return sb.toString();
    }

    public static String renderRemoteAddrs(RemoteAddresses remoteAddresses, boolean isAdoc) {
        if (remoteAddresses.remoteAddrsMap().isEmpty()) {
            return "";
        }

        var sb = new StringBuilder();

        int firstColumnWidth = calculateFirstColumnWidth(remoteAddresses);
        int secondColumnWidth = calculateValuesColumnWidth(remoteAddresses);

        firstColumnWidth = Math.max(firstColumnWidth, REMOTE_ADDR_FIRST_COLUMN_HEADER.length());
        secondColumnWidth = Math.max(secondColumnWidth, REMOTE_ADDR_SECOND_COLUMN_HEADER.length());

        appendHeader(
            sb,
            REMOTE_ADDR_FIRST_COLUMN_HEADER,
            REMOTE_ADDR_SECOND_COLUMN_HEADER,
            firstColumnWidth,
            secondColumnWidth,
            isAdoc
        );

        populateTable(sb, remoteAddresses, firstColumnWidth, secondColumnWidth, 0, isAdoc);

        return sb.toString();
    }

    public static String renderUserAgents(UserAgents userAgents, boolean isAdoc) {
        if (userAgents.userAgentsMap().isEmpty()) {
            return "";
        }

        var sb = new StringBuilder();

        int firstColumnWidth = calculateFirstColumnWidth(userAgents);
        int secondColumnWidth = calculateValuesColumnWidth(userAgents);

        firstColumnWidth = Math.max(firstColumnWidth, USER_AGENTS_FIRST_COLUMN_HEADER.length());
        secondColumnWidth = Math.max(secondColumnWidth, USER_AGENTS_SECOND_COLUMN_HEADER.length());

        appendHeader(
            sb,
            USER_AGENTS_FIRST_COLUMN_HEADER,
            USER_AGENTS_SECOND_COLUMN_HEADER,
            firstColumnWidth,
            secondColumnWidth,
            isAdoc
        );

        populateTable(sb, userAgents, firstColumnWidth, secondColumnWidth, 0, isAdoc);

        return sb.toString();
    }

    private static int calculateFirstColumnWidth(Record info) {

        return switch (info) {
            case GeneralInfo generalInfo -> generalInfo.generalInfoMap()
                .keySet()
                .stream()
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            case Resources resources -> resources.resourcesMap()
                .keySet()
                .stream()
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            case RemoteAddresses addrs -> addrs.remoteAddrsMap()
                .keySet()
                .stream()
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            case UserAgents agents -> agents.userAgentsMap()
                .keySet()
                .stream()
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            default -> 0;
        };
    }

    private static int calculateValuesColumnWidth(Record info) {

        return switch (info) {
            case GeneralInfo generalInfo -> generalInfo.generalInfoMap()
                .values()
                .stream()
                .map(String::valueOf)
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            case Resources resources -> resources.resourcesMap()
                .values()
                .stream()
                .map(String::valueOf)
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            case RemoteAddresses addrs -> addrs.remoteAddrsMap()
                .values()
                .stream()
                .map(String::valueOf)
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            case UserAgents agents -> agents.userAgentsMap()
                .values()
                .stream()
                .map(String::valueOf)
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            case StatusCodes statusCodes -> statusCodes.statusCodeMap()
                .values()
                .stream()
                .map(String::valueOf)
                .max(Comparator.comparing(String::length))
                .orElse("")
                .length();
            default -> 0;
        };
    }

    private static void appendHeader(
        StringBuilder sb,
        String firstCol,
        String secondCol,
        int firstColumnWidth,
        int secondColumnWidth,
        boolean isAdoc
    ) {
        sb.append(getFormattedString(
            firstCol,
            secondCol,
            "",
            firstColumnWidth + 1,
            secondColumnWidth + 1,
            0,
            isAdoc
        ));

        if (!isAdoc) {
            sb.append(PIPE)
                .append("-".repeat(firstColumnWidth + 1))
                .append(PIPE)
                .append("-".repeat(secondColumnWidth + 1))
                .append(PIPE_NEWLINE);
        }
    }

    @SuppressWarnings("ParameterNumber")
    private static void appendWideHeader(
        StringBuilder sb,
        String firstCol,
        String secondCol,
        String thirdCol,
        int firstColumnWidth,
        int secondColumnWidth,
        int thirdColumnWidth,
        boolean isAdoc
    ) {
        sb.append(getFormattedString(
            firstCol,
            secondCol,
            thirdCol,
            firstColumnWidth,
            secondColumnWidth,
            thirdColumnWidth,
            isAdoc
        ));

        if (!isAdoc) {
            sb.append(PIPE)
                .append("-".repeat(firstColumnWidth))
                .append(PIPE)
                .append("-".repeat(secondColumnWidth))
                .append(PIPE).append("-".repeat(thirdColumnWidth))
                .append(PIPE_NEWLINE);
        }
    }

    private static void populateTable(
        StringBuilder sb,
        Record info,
        int firstColumnWidth,
        int secondColumnWidth,
        int thirdColumnWidth,
        boolean isAdoc
    ) {
        switch (info) {
            case GeneralInfo generalInfo -> {
                for (var entry : generalInfo.generalInfoMap().entrySet()) {
                    sb.append(getFormattedString(
                        entry.getKey(),
                        String.valueOf(entry.getValue()),
                        "",
                        firstColumnWidth + 1,
                        secondColumnWidth + 1,
                        0,
                        isAdoc
                    ));
                }
            }
            case Resources resources -> {
                for (var entry : resources.resourcesMap().entrySet()) {
                    sb.append(getFormattedString(
                        entry.getKey(),
                        String.valueOf(entry.getValue()),
                        "",
                        firstColumnWidth + 1,
                        secondColumnWidth + 1,
                        0,
                        isAdoc
                    ));
                }
            }
            case RemoteAddresses addrs -> {
                for (var entry : addrs.remoteAddrsMap().entrySet()) {
                    sb.append(getFormattedString(
                        entry.getKey().trim(),
                        String.valueOf(entry.getValue()),
                        "",
                        firstColumnWidth + 1,
                        secondColumnWidth + 1,
                        0,
                        isAdoc
                    ));
                }
            }
            case UserAgents agents -> {
                for (var entry : agents.userAgentsMap().entrySet()) {
                    sb.append(getFormattedString(
                        entry.getKey(),
                        String.valueOf(entry.getValue()),
                        "",
                        firstColumnWidth + 1,
                        secondColumnWidth + 1,
                        0,
                        isAdoc
                    ));
                }
            }
            case StatusCodes codes -> {
                for (var entry : codes.statusCodeMap().entrySet()) {
                    sb.append(getFormattedString(
                        String.valueOf(entry.getKey()),
                        HttpStatus.getStatusText(entry.getKey()),
                        String.valueOf(entry.getValue()),
                        firstColumnWidth,
                        secondColumnWidth,
                        thirdColumnWidth,
                        isAdoc
                    ));
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + info);
        }
    }

    private static String getFormattedString(
        String firstColumnItem,
        String secondColumnItem,
        String thirdColumnItem,
        int firstColumnWidth,
        int secondColumnWidth,
        int thirdColumnWidth,
        boolean isAdoc
    ) {
        String firstColumnTabulation = " ".repeat(firstColumnWidth - firstColumnItem.length());
        String secondColumnTabulation = " ".repeat(secondColumnWidth - secondColumnItem.length());
        String rightBorder = PIPE_NEWLINE;
        if (isAdoc) {
            rightBorder = "\n";
        }

        StringBuilder sb = new StringBuilder();
        if (thirdColumnWidth == 0) {
            sb.append(PIPE)
                .append(firstColumnItem)
                .append(firstColumnTabulation)
                .append(PIPE)
                .append(secondColumnTabulation)
                .append(secondColumnItem)
                .append(rightBorder);
        } else {
            sb.append(PIPE)
                .append(firstColumnItem)
                .append(firstColumnTabulation)
                .append(PIPE)
                .append(secondColumnTabulation)
                .append(secondColumnItem)
                .append(PIPE)
                .append(" ".repeat(thirdColumnWidth - thirdColumnItem.length()))
                .append(thirdColumnItem)
                .append(rightBorder);
        }

        return sb.toString();
    }
}
