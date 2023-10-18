package edu.hw2.task4;

public class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        var temp = new Exception();
        var stackTrace = temp.getStackTrace();
        return new CallingInfo(stackTrace[1].getClassName(), stackTrace[1].getMethodName());
    }

    public record CallingInfo(String className, String methodName) {
    }

}
