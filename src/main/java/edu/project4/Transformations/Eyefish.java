package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformation;
import java.awt.Color;
import static java.lang.Math.sqrt;

public record Eyefish(Color color, double weight) implements Transformation {
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

        double r = sqrt(x * x + y * y);

        double newX = 2.0 / (r + 1) * x;
        double newY = 2.0 / (r + 1) * y;

        return new Point(newX, newY);
    }
}
