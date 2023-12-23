package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformation;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public record Blur(Color color, double weight) implements Transformation {
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
        double psi1 = ThreadLocalRandom.current().nextDouble();
        double psi2 = ThreadLocalRandom.current().nextDouble();

        double newX = psi1 * cos(2 * Math.PI * psi2);
        double newY = psi1 * sin(2 * Math.PI * psi2);

        return new Point(newX, newY);
    }
}
