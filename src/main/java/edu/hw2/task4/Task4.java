package edu.hw2.task4;

import edu.hw2.task1.Expr;

public class Task4 {
    public record CallingInfo(String className, String methodName) {}

    public static CallingInfo callingInfo() {
        var temp = new Exception();
        var stackTrace = temp.getStackTrace();
        return new CallingInfo(stackTrace[1].getClassName(), stackTrace[1].getMethodName());
    }

    public static void main(String[] args) {
        var temp = callingInfo();
        System.out.println(temp.className + " " + temp.methodName);
    }
}
