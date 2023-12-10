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

        return new Point(a * x + b * y + c, d * x + e * y + f);
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
