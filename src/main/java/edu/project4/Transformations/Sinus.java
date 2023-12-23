package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformation;
import java.awt.Color;
import static java.lang.Math.sin;

public record Sinus(Color color, double weight) implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = sin(point.x());
        double newY = sin(point.y());

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
