package edu.project3;

import java.util.Comparator;
import org.apache.commons.httpclient.HttpStatus;

public class DefaultRenderer {

    public String renderGeneralInfo(GeneralInfo generalInfo) {
        var sb = new StringBuilder();

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(HttpStatus.getStatusText(500));
    }

    public String renderResources(Resources resources) {
        final String FIRST_COLUMN_HEADER = "RESOURCE";
        final String SECOND_COLUMN_HEADER = "COUNT";

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

        sb.append("#### Resources").append("\n").append("\n");

        sb.append(getFormattedString(
            FIRST_COLUMN_HEADER,
            SECOND_COLUMN_HEADER,
            "",
            firstColumnWidth,
            secondColumnWidth,
            0
        ));

        sb.append("|")
            .append("-".repeat(firstColumnWidth))
            .append("|")
            .append("-".repeat(secondColumnWidth))
            .append("|");

        for (var entry : resources.resourcesMap().entrySet()) {
            sb.append(getFormattedString(
                entry.getKey(),
                String.valueOf(entry.getValue()),
                "",
                firstColumnWidth,
                secondColumnWidth,
                0
            ));
        }

        return sb.toString();
    }

    public String renderStatusCodes(StatusCodes statusCodes) {
        final String FIRST_COLUMN_HEADER = "STATUS CODE";
        final String SECOND_COLUMN_HEADER = "DESCRIPTION";
        final String THIRD_COLUMN_HEADER = "COUNT";

        var sb = new StringBuilder();

        int firstColumnWidth = FIRST_COLUMN_HEADER.length() + 2;
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

        sb.append("#### Status codes").append("\n").append("\n");

        sb.append(getFormattedString(
            FIRST_COLUMN_HEADER,
            SECOND_COLUMN_HEADER,
            THIRD_COLUMN_HEADER,
            firstColumnWidth,
            secondColumnWidth,
            thirdColumnWidth
        ));

        sb.append("|")
            .append("-".repeat(firstColumnWidth))
            .append("|")
            .append("-".repeat(secondColumnWidth))
            .append("|").append("-".repeat(thirdColumnWidth))
            .append("|\n");

        for (var entry : statusCodes.statusCodeMap().entrySet()) {
            sb.append(getFormattedString(
                String.valueOf(entry.getKey()),
                HttpStatus.getStatusText(entry.getKey()),
                String.valueOf(entry.getValue()),
                firstColumnWidth,
                secondColumnWidth,
                thirdColumnWidth
            ));
        }

        return sb.toString();
    }

    private String getFormattedString(
        String firstColumnItem,
        String secondColumnItem,
        String thirdColumnItem,
        int firstColumnWidth,
        int secondColumnWidth,
        int thirdColumnWidth
    ) {
        String firstColumnTabulation = " ".repeat(firstColumnWidth - firstColumnItem.length());
        String secondColumnTabulation = " ".repeat(secondColumnWidth - secondColumnItem.length());
        if (thirdColumnWidth == 0) {
            return "|" +
                firstColumnItem +
                firstColumnTabulation +
                "|" +
                secondColumnTabulation +
                secondColumnItem +
                "|\n";
        } else {
            return "|" +
                firstColumnItem +
                firstColumnTabulation +
                "|" +
                secondColumnTabulation +
                secondColumnItem +
                "|" +
                " ".repeat(thirdColumnWidth - thirdColumnItem.length()) +
                thirdColumnItem +
                "|\n";
        }
    }
}
