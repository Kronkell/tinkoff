package edu.project4;

import java.awt.Color;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    Color getColor();

    double getWeight();
}
