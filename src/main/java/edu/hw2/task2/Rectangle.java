package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    private void setWidth(int width) {
        this.width = width;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public boolean setSides(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
        return true;
    }

    public double area() {
        return width * height;
    }
}
