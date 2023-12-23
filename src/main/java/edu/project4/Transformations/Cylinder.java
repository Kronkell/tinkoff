package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformation;
import java.awt.Color;
import static java.lang.Math.sin;

public record Cylinder(Color color, double weight) implements Transformation {
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = sin(x);
        double newY = y;

        return new Point(newX, newY);
    }
}
