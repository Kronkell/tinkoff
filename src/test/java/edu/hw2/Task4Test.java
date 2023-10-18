package edu.hw2;

import edu.hw2.task4.Task4;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    public void shouldReturnTask4TestGivenThisClass() {
        String expectedInfo = "edu.hw2.Task4Test";
        var callInfo = Task4.callingInfo();

        assertThat(callInfo.className()).isEqualTo(expectedInfo);
    }

    @Test
    public void shouldReturnMethodNameGivenThisMethod() {
        String expectedInfo = "shouldReturnMethodNameGivenThisMethod";
        var callInfo = Task4.callingInfo();

        assertThat(callInfo.methodName()).isEqualTo(expectedInfo);
    }
}
