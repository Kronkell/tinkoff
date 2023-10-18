package edu.hw1;

public class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        if (n < 0 || shift < 0) {
            throw new IllegalArgumentException("Wrong input!");
        }

        int bitCount = Integer.toBinaryString(n).length();
        int newShift = shift % bitCount;

        if (newShift == 0) {
            return n;
        }

        int divisor = (int) Math.pow(2, bitCount - newShift);

        int leftPart = (n % divisor) << newShift;
        int rightPart = n >>> (bitCount - newShift);

        return leftPart | rightPart;
    }

    public static int rotateRight(int n, int shift) {
        if (n < 0 || shift < 0) {
            throw new IllegalArgumentException("Wrong input.");
        }

        int bitCount = Integer.toBinaryString(n).length();
        int newShift = shift % bitCount;

        if (newShift == 0) {
            return n;
        }

        int divisor = (int) Math.pow(2, newShift);

        int leftPart = (n % divisor) << (bitCount - newShift);
        int rightPart = n >>> newShift;

        return leftPart | rightPart;

    }
}
