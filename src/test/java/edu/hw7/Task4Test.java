package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    public void shouldCalculatePIWithGivenPrecisionSingleThreaded() {
        long N = 1_000_000_000L;
        double expectedRelativeError = 0.002;

        double pi = Task4.singleThreadMonteCarlo(N);
        double actualRelativeError = (Math.abs(Math.PI - pi) / Math.PI) * 100;

        assertThat(actualRelativeError).isLessThan(expectedRelativeError);
    }
    @Test
    public void shouldCalculatePIWithGivenPrecision() throws InterruptedException {
        long N = 1_000_000_000L;
        double expectedRelativeError = 0.002;

        double pi = Task4.multiThreadMonteCarlo(N, Runtime.getRuntime().availableProcessors());
        double actualRelativeError = (Math.abs(Math.PI - pi) / Math.PI) * 100;

        assertThat(actualRelativeError).isLessThan(expectedRelativeError);
    }
}
