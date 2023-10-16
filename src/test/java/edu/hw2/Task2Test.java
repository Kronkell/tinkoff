package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        if (rect.setSides(20, 10)) {
            assertThat(rect.area()).isEqualTo(200.0);
        }
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea1(Rectangle rect) {
        if (rect.setSides(20, 20)) {
            assertThat(rect.area()).isEqualTo(400.0);
        }
    }
}
