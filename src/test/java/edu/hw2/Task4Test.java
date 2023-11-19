package edu.hw2;

import edu.hw2.task4.Task4;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    public void shouldReturnCallingInfoGivenClassAndMethod() {
        Task4.CallingInfo expectedInfo = new Task4.CallingInfo(
            "edu.hw2.Task4Test",
            "shouldReturnCallingInfoGivenClassAndMethod"
        );

        var callInfo = Task4.callingInfo();

        assertThat(callInfo).isEqualTo(expectedInfo);
    }
}
