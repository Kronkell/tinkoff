package edu.hw7;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    public void checkIfMultithreadedFactIsFaster() {
        long N = 15000;
        long start = System.nanoTime();
        Task2.singleThreadedFactorial(N);
        long singleFactTime = System.nanoTime() - start;

        start = System.nanoTime();
        Task2.multiThreadedFactorial(N);
        long multiFactTime = System.nanoTime() - start;

        assertThat(singleFactTime).isGreaterThan(multiFactTime);
    }

    @Test
    public void shouldCalculateFactorialGivenNumber() {
        long N = 150;
        BigInteger expectedResult = new BigInteger("571338395644585459047893286526" +
            "1054003189553578601126418254837583317982912484539839312657448867531114" +
            "5377107878746854204162666250198684504466355949195922066574942592095735" +
            "7789293253572904449624724054167907221184454371222696755200000000000000" +
            "00000000000000000000000");

        BigInteger actualResult = Task2.multiThreadedFactorial(N);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
