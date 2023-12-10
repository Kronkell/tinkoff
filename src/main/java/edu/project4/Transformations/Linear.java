package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformation;
import java.awt.Color;

public record Linear(Color color, double weight) implements Transformation {
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
        return point;
    }
}
