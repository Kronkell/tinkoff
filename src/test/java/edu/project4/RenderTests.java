package edu.project4;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import edu.project4.Transformations.Handkerchief;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.project4.ChaosGame.randomAffineTransformation;
import static edu.project4.ChaosGame.render;

public class RenderTests {
    @Test
    public void shouldRenderCorrectly() {
        Pixel[][] data = new Pixel[1920][1080];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                data[i][j] = new Pixel(Color.black, 0, 0);
            }
        }
        var affineTransformations = List.of(
            randomAffineTransformation(),
            randomAffineTransformation()
        );

        Assertions.assertDoesNotThrow(() ->
            render(
                new FractalImage(data, 1920, 1080),
                affineTransformations,
                Collections.singletonList(new Handkerchief(Color.WHITE, 1)),
                1,
                100
            ));
    }
}
