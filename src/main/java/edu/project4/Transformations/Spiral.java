package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformation;
import java.awt.Color;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public record Spiral(Color color, double weight) implements Transformation {
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

        double theta = Math.atan(x / y);
        double r = sqrt(x * x + y * y);

        double newX = 1 / r * (cos(theta) + sin(r));
        double newY = 1 / r * (sin(theta) - cos(r));

        return new Point(newX, newY);
    }
}
