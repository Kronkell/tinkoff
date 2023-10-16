package edu.hw2.task2;

public class Square extends Rectangle {
    @Override
    public boolean setSides(int width, int height) {
        if (width != height) {
            return false;
        }
        super.setSides(width, height);
        return true;
    }
}
