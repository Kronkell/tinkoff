package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@SuppressWarnings("RegexpSinglelineJava")
public class LogAnalyser {

    public static final String PATH = "path";
    public static final String FROM = "from";
    public static final String FORMAT = "format";

    private LogAnalyser() {
    }

    public static void run(String[] args) throws ParseException, IOException {
        CommandLineParser parser = new DefaultParser();

        Option pathOption = new Option(null, PATH, true, "data sources");
        pathOption.setArgs(Option.UNLIMITED_VALUES);

        Option fromOption = new Option(null, FROM, true, "From date");
        fromOption.setOptionalArg(true);

        Option toOption = new Option(null, "to", true, "To date");
        toOption.setOptionalArg(true);

        Option formatOption = new Option(null, FORMAT, true, "Output format");
        formatOption.setOptionalArg(true);

        Options options = new Options();
        options
            .addOption(pathOption)
            .addOption(fromOption)
            .addOption(formatOption)
            .addOption(toOption);

        CommandLine cmd = parser.parse(options, args);

        List<String> rawLogs = new ArrayList<>();
        for (var path : cmd.getOptionValues(PATH)) {
            List<String> curr;
            if (path.startsWith("http")) {
                curr = LogLoader.loadLogsFromURL(URI.create(path).toURL());
            } else {
                curr = LogLoader.loadLogsFromFile(Path.of(path));
            }
            rawLogs.addAll(curr);
        }

        LogReport report = new LogReport(
            LogParser.formListOfLogs(rawLogs),
            List.of(cmd.getOptionValues(PATH)),
            cmd.getOptionValue(FROM),
            cmd.getOptionValue("to")
        );

        final String GENERAL_INFO = "#### General Info\n\n";
        final String RESOURCES = "#### Resources\n\n";
        final String STATUS_CODES = "#### Status codes\n\n";
        final String REMOTE_ADDRESSES = "#### Remote addresses\n\n";
        final String USER_AGENTS = "#### User agents\n\n";

        String generalInfo = DefaultRenderer.renderGeneralInfo(report.getGeneralInfo(), false);
        String resources = DefaultRenderer.renderResources(report.getResources(), false);
        String statusCodes = DefaultRenderer.renderStatusCodes(report.getStatusCodes(), false);
        String remoteAddrs = DefaultRenderer.renderRemoteAddrs(report.getRemoteAddresses(), false);
        String userAgents = DefaultRenderer.renderUserAgents(report.getUserAgents(), false);
        String output;

        if (cmd.hasOption(FORMAT)) {
            if (cmd.getOptionValue(FORMAT).equals("markdown")) {
                output =
                    GENERAL_INFO + generalInfo
                        + RESOURCES + resources
                        + STATUS_CODES + statusCodes
                        + REMOTE_ADDRESSES + remoteAddrs
                        + USER_AGENTS + userAgents;

                Files.write(Path.of("Report.md"), output.getBytes());
            } else if (cmd.getOptionValue(FORMAT).equals("adoc")) {
                final String delimiter = "|===\n";

                generalInfo = DefaultRenderer.renderGeneralInfo(report.getGeneralInfo(), true);
                resources = DefaultRenderer.renderResources(report.getResources(), true);
                statusCodes = DefaultRenderer.renderStatusCodes(report.getStatusCodes(), true);
                remoteAddrs = DefaultRenderer.renderRemoteAddrs(report.getRemoteAddresses(), true);
                userAgents = DefaultRenderer.renderUserAgents(report.getUserAgents(), true);

                output =
                    GENERAL_INFO
                        + delimiter
                        + generalInfo
                        + delimiter
                        + RESOURCES
                        + delimiter
                        + resources
                        + delimiter
                        + STATUS_CODES
                        + delimiter
                        + statusCodes
                        + delimiter
                        + REMOTE_ADDRESSES
                        + delimiter
                        + remoteAddrs
                        + delimiter
                        + USER_AGENTS
                        + delimiter
                        + userAgents
                        + delimiter;

                Files.write(Path.of("Report.adoc"), output.getBytes());
            }
        } else {
            System.out.println(GENERAL_INFO + generalInfo);
            System.out.println(RESOURCES + resources);
            System.out.println(STATUS_CODES + statusCodes);
            System.out.println(REMOTE_ADDRESSES + remoteAddrs);
            System.out.println(USER_AGENTS + userAgents);
        }
    }
}
