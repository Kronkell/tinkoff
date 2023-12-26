package edu.hw6;

import org.junit.jupiter.api.Test;
import static edu.hw6.Task6.printSeveralPorts;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    public void shouldPrintSeveralPorts() {
        String expectedOutput = "Protocol         Port   Service\n" +
            "TCP, UDP, SCTP   22     SSH-SCP\n" +
            "TCP, UDP         135    Microsoft EPMAP\n" +
            "TCP, UDP         137    NetBIOS-ns\n" +
            "TCP, UDP         139    NetBIOS-ssn\n" +
            "TCP, UDP         2177   qWAVE Bandwidth Estimate\n" +
            "TCP, UDP         4500   IPsec NAT-Traversal\n";

        String actualOutput = printSeveralPorts();

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}
