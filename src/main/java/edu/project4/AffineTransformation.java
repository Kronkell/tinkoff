package edu.project4;

import java.awt.Color;

public record AffineTransformation(
    double a,
    double b,
    double c,
    double d,
    double e,
    double f,
    Color color,
    double r,
    double g,
    double blue
) implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = a * x + b * y + c;
        double newY = d * x + e * y + f;

        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getWeight() {
        return 0;
    }
}
