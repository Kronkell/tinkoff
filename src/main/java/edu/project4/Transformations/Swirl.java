package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformation;
import java.awt.Color;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public record Swirl(Color color, double weight) implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double r = x * x + y * y;

        double newX = x * sin(r) - y * cos(r);
        double newY = x * cos(r) + y * sin(r);

        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
