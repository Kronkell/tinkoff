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
import static edu.project3.StringConsts.DATA_SOURCES;
import static edu.project3.StringConsts.FORMAT;
import static edu.project3.StringConsts.FROM;
import static edu.project3.StringConsts.FROM_DATE;
import static edu.project3.StringConsts.GENERAL_INFO;
import static edu.project3.StringConsts.OUTPUT_FORMAT;
import static edu.project3.StringConsts.PATH;
import static edu.project3.StringConsts.REMOTE_ADDRESSES;
import static edu.project3.StringConsts.RESOURCES;
import static edu.project3.StringConsts.STATUS_CODES;
import static edu.project3.StringConsts.TO;
import static edu.project3.StringConsts.TO_DATE;
import static edu.project3.StringConsts.USER_AGENTS;

@SuppressWarnings("RegexpSinglelineJava")
public class LogAnalyser {
    private LogAnalyser() {
    }

    public static void run(String[] args) throws ParseException, IOException {
        CommandLineParser parser = new DefaultParser();

        Option pathOption = new Option(null, PATH, true, DATA_SOURCES);
        pathOption.setArgs(Option.UNLIMITED_VALUES);

        Option fromOption = new Option(null, FROM, true, FROM_DATE);
        fromOption.setOptionalArg(true);

        Option toOption = new Option(null, TO, true, TO_DATE);
        toOption.setOptionalArg(true);

        Option formatOption = new Option(null, FORMAT, true, OUTPUT_FORMAT);
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
            cmd.getOptionValue(TO)
        );

        String generalInfo = DefaultRenderer.renderGeneralInfo(report.getInfo().generalInfo(), false);
        String resources = DefaultRenderer.renderResources(report.getInfo().resources(), false);
        String statusCodes = DefaultRenderer.renderStatusCodes(report.getInfo().statusCodes(), false);
        String remoteAddrs = DefaultRenderer.renderRemoteAddrs(report.getInfo().remoteAddresses(), false);
        String userAgents = DefaultRenderer.renderUserAgents(report.getInfo().userAgents(), false);
        StringBuilder output = new StringBuilder();

        if (cmd.hasOption(FORMAT)) {
            if (cmd.getOptionValue(FORMAT).equals("markdown")) {
                output
                    .append(GENERAL_INFO)
                    .append(generalInfo)
                    .append(RESOURCES)
                    .append(resources)
                    .append(STATUS_CODES)
                    .append(statusCodes)
                    .append(REMOTE_ADDRESSES)
                    .append(remoteAddrs)
                    .append(USER_AGENTS)
                    .append(userAgents);

                Files.write(Path.of("Report.md"), output.toString().getBytes());
            } else if (cmd.getOptionValue(FORMAT).equals("adoc")) {
                String delimiter = "|===\n";

                generalInfo = DefaultRenderer.renderGeneralInfo(report.getInfo().generalInfo(), true);
                resources = DefaultRenderer.renderResources(report.getInfo().resources(), true);
                statusCodes = DefaultRenderer.renderStatusCodes(report.getInfo().statusCodes(), true);
                remoteAddrs = DefaultRenderer.renderRemoteAddrs(report.getInfo().remoteAddresses(), true);
                userAgents = DefaultRenderer.renderUserAgents(report.getInfo().userAgents(), true);

                output
                    .append(GENERAL_INFO)
                    .append(delimiter)
                    .append(generalInfo)
                    .append(delimiter)
                    .append(RESOURCES)
                    .append(delimiter)
                    .append(resources)
                    .append(delimiter)
                    .append(STATUS_CODES)
                    .append(delimiter)
                    .append(statusCodes)
                    .append(delimiter)
                    .append(REMOTE_ADDRESSES)
                    .append(delimiter)
                    .append(remoteAddrs)
                    .append(delimiter)
                    .append(USER_AGENTS)
                    .append(delimiter)
                    .append(userAgents)
                    .append(delimiter);

                Files.write(Path.of("Report.adoc"), output.toString().getBytes());
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
