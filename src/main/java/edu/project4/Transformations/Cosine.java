package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformation;
import java.awt.Color;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.cosh;
import static java.lang.Math.sin;
import static java.lang.Math.sinh;

public record Cosine(Color color, double weight) implements Transformation {
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

        return new Point(cos(PI * x) * cosh(y), -sin(PI * x) * sinh(y));
    }
}
