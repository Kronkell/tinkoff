package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber", "OperatorWrap", "LocalVariableName"})
public class Task6 {
    static final LinkedHashMap<Integer, String> PORT_INFO = new LinkedHashMap<>();

    static {
        PORT_INFO.put(22, "TCP, UDP, SCTP;SSH-SCP");
        PORT_INFO.put(135, "TCP, UDP;Microsoft EPMAP");
        PORT_INFO.put(137, "TCP, UDP;NetBIOS-ns");
        PORT_INFO.put(139, "TCP, UDP;NetBIOS-ssn");
        PORT_INFO.put(2177, "TCP, UDP;qWAVE Bandwidth Estimate");
        PORT_INFO.put(4500, "TCP, UDP;IPsec NAT-Traversal");
        PORT_INFO.put(5432, "TCP;PostgreSQL");
        PORT_INFO.put(8100, "TCP, UDP;Xprint Server");
        PORT_INFO.put(9300, "TCP, UDP;Virtual Racing Service");
    }

    static final String HEADER = "Protocol         Port   Service\n";
    static final int FIRST_ROW_WIDTH = 17;
    static final int SECOND_ROW_WIDTH = 7;

    private Task6() {
    }

    public static boolean isAvailable(int port) {
        try (var ss = new ServerSocket(port); var ds = new DatagramSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String printSeveralPorts() {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER);
        for (Map.Entry<Integer, String> k : PORT_INFO.entrySet()) {
            if (!isAvailable(k.getKey())) {
                var infoPair = k.getValue().split(";");

                sb.append(infoPair[0])
                    .append(" ".repeat(FIRST_ROW_WIDTH - infoPair[0].length()))
                    .append(k.getKey().toString()).append(" ".repeat(SECOND_ROW_WIDTH - k.getKey().toString().length()))
                    .append(infoPair[1])
                    .append("\n");
            }
        }

        return sb.toString();
    }
}
