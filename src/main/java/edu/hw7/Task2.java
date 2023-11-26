package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Task2 {
    private Task2() {
    }

    public static BigInteger multiThreadedFactorial(long n) {
        return LongStream
            .rangeClosed(1, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public static BigInteger singleThreadedFactorial(long n) {
        return LongStream
            .rangeClosed(1, n)
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
