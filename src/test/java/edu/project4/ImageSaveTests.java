package edu.project4;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImageSaveTests {
    @Test
    public void shouldSaveImageToFile(@TempDir Path path) throws IOException {
        Pixel[][] data = new Pixel[1920][1080];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                data[i][j] = new Pixel(Color.black, 0, 0);
            }
        }

        ImageUtils.save(
            new FractalImage(data, 1920, 1080),
            path.resolve("flame"),
            ImageFormat.PNG
        );

        assertThat(Files.exists(path.resolve("flame.png"))).isTrue();
    }

}
