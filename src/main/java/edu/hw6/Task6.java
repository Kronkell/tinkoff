package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import static java.util.Map.entry;

@SuppressWarnings({"UncommentedMain", "RegexpSinglelineJava", "MagicNumber", "OperatorWrap", "LocalVariableName"})
public class Task6 {
    private Task6() {
    }

    public static boolean isAvailable(int port) {
        try (var ss = new ServerSocket(port); var ds = new DatagramSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void printSeveralPorts() {
        final Map<Integer, String> PORT_INFO = Map.ofEntries(
            entry(22, "TCP, UDP, SCTP;SSH-SCP"),
            entry(135, "TCP, UDP;Microsoft EPMAP"),
            entry(137, "TCP, UDP;NetBIOS-ns"),
            entry(139, "TCP, UDP;NetBIOS-ssn"),
            entry(2177, "TCP, UDP;qWAVE Bandwidth Estimate"),
            entry(4500, "TCP, UDP;IPsec NAT-Traversal"),
            entry(5432, "TCP;PostgreSQL"),
            entry(8100, "TCP, UDP;Xprint Server"),
            entry(9300, "TCP, UDP;Virtual Racing Service")
        );
        final String HEADER = "Protocol         Port   Service";
        final int FIRST_ROW_WIDTH = 17;
        final int SECOND_ROW_WIDTH = 7;

        System.out.println(HEADER);
        for (var k : PORT_INFO.entrySet()) {
            if (!isAvailable(k.getKey())) {
                var infoPair = k.getValue().split(";");

                System.out.println(infoPair[0] + " ".repeat(FIRST_ROW_WIDTH - infoPair[0].length()) +
                    k.getKey().toString() + " ".repeat(SECOND_ROW_WIDTH - k.getKey().toString().length()) +
                    infoPair[1]);
            }
        }

    }

    public static void main(String[] args) {
        int MIN_PORT = 0;
        int MAX_PORT = 49151;

        for (int i = MIN_PORT; i < MAX_PORT; ++i) {
            if (!isAvailable(i)) {
                System.out.println(i);
            }
        }

        printSeveralPorts();
    }
}
