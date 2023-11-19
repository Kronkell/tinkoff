package edu.project3;

import java.util.Comparator;
import org.apache.commons.httpclient.HttpStatus;

public class DefaultRenderer {
    private DefaultRenderer() {
    }

    private static final String PIPE = "|";
    private static final String PIPE_NEWLINE = "|\n";
    private static final String COUNT = "*COUNT*";

    public static String renderGeneralInfo(GeneralInfo generalInfo, boolean isAdoc) {
        final String FIRST_COLUMN_HEADER = "*METRIC*";
        final String SECOND_COLUMN_HEADER = "*VALUE*";

        var sb = new StringBuilder();
        int firstColumnWidth = generalInfo.generalInfoMap()
            .keySet()
            .stream()
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        int secondColumnWidth = generalInfo.generalInfoMap()
            .values()
            .stream()
            .map(String::valueOf)
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        sb.append(getFormattedString(
            FIRST_COLUMN_HEADER,
            SECOND_COLUMN_HEADER,
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

        return sb.toString();
    }

    public static String renderResources(Resources resources, boolean isAdoc) {
        if (resources.resourcesMap().isEmpty()) {
            return "";
        }

        final String FIRST_COLUMN_HEADER = "*RESOURCE*";
        final String SECOND_COLUMN_HEADER = COUNT;

        var sb = new StringBuilder();
        int firstColumnWidth = resources.resourcesMap()
            .keySet()
            .stream()
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        int secondColumnWidth = resources.resourcesMap()
            .values()
            .stream()
            .map(String::valueOf)
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        firstColumnWidth = Math.max(firstColumnWidth, FIRST_COLUMN_HEADER.length());
        secondColumnWidth = Math.max(secondColumnWidth, SECOND_COLUMN_HEADER.length());

        sb.append(getFormattedString(
            FIRST_COLUMN_HEADER,
            SECOND_COLUMN_HEADER,
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

        return sb.toString();
    }

    public static String renderStatusCodes(StatusCodes statusCodes, boolean isAdoc) {
        if (statusCodes.statusCodeMap().isEmpty()) {
            return "";
        }

        final String FIRST_COLUMN_HEADER = "*STATUS CODE*";
        final String SECOND_COLUMN_HEADER = "*DESCRIPTION*";
        final String THIRD_COLUMN_HEADER = COUNT;

        var sb = new StringBuilder();

        int firstColumnWidth = FIRST_COLUMN_HEADER.length();
        int secondColumnWidth = statusCodes.statusCodeMap()
            .keySet()
            .stream()
            .map(HttpStatus::getStatusText)
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();
        int thirdColumnWidth = statusCodes.statusCodeMap()
            .values()
            .stream()
            .map(String::valueOf)
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        secondColumnWidth = Math.max(secondColumnWidth, SECOND_COLUMN_HEADER.length());
        thirdColumnWidth = Math.max(thirdColumnWidth, THIRD_COLUMN_HEADER.length());

        sb.append(getFormattedString(
            FIRST_COLUMN_HEADER,
            SECOND_COLUMN_HEADER,
            THIRD_COLUMN_HEADER,
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

        for (var entry : statusCodes.statusCodeMap().entrySet()) {
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

        return sb.toString();
    }

    public static String renderRemoteAddrs(RemoteAddresses remoteAddresses, boolean isAdoc) {
        if (remoteAddresses.remoteAddrsMap().isEmpty()) {
            return "";
        }

        final String FIRST_COLUMN_HEADER = "*REMOTE ADDRESS*";
        final String SECOND_COLUMN_HEADER = COUNT;

        var sb = new StringBuilder();
        int firstColumnWidth = remoteAddresses.remoteAddrsMap()
            .keySet()
            .stream()
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        int secondColumnWidth = remoteAddresses.remoteAddrsMap()
            .values()
            .stream()
            .map(String::valueOf)
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        firstColumnWidth = Math.max(firstColumnWidth, FIRST_COLUMN_HEADER.length());
        secondColumnWidth = Math.max(secondColumnWidth, SECOND_COLUMN_HEADER.length());

        sb.append(getFormattedString(
            FIRST_COLUMN_HEADER,
            SECOND_COLUMN_HEADER,
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

        for (var entry : remoteAddresses.remoteAddrsMap().entrySet()) {
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

        return sb.toString();
    }

    public static String renderUserAgents(UserAgents userAgents, boolean isAdoc) {
        if (userAgents.userAgentsMap().isEmpty()) {
            return "";
        }

        final String FIRST_COLUMN_HEADER = "*USER AGENT*";
        final String SECOND_COLUMN_HEADER = COUNT;

        var sb = new StringBuilder();
        int firstColumnWidth = userAgents.userAgentsMap()
            .keySet()
            .stream()
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        int secondColumnWidth = userAgents.userAgentsMap()
            .values()
            .stream()
            .map(String::valueOf)
            .max(Comparator.comparing(String::length))
            .orElse("")
            .length();

        firstColumnWidth = Math.max(firstColumnWidth, FIRST_COLUMN_HEADER.length());
        secondColumnWidth = Math.max(secondColumnWidth, SECOND_COLUMN_HEADER.length());

        sb.append(getFormattedString(
            FIRST_COLUMN_HEADER,
            SECOND_COLUMN_HEADER,
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

        for (var entry : userAgents.userAgentsMap().entrySet()) {
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

        return sb.toString();
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

        if (thirdColumnWidth == 0) {
            return PIPE
                + firstColumnItem
                + firstColumnTabulation
                + PIPE
                + secondColumnTabulation
                + secondColumnItem
                +
                rightBorder;
        } else {
            return PIPE
                +
                firstColumnItem
                + firstColumnTabulation
                + PIPE
                + secondColumnTabulation
                + secondColumnItem
                + PIPE
                + " ".repeat(thirdColumnWidth - thirdColumnItem.length())
                + thirdColumnItem
                + rightBorder;
        }
    }
}
