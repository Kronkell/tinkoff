package edu.project4;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import edu.project4.Transformations.Heart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static edu.project4.ChaosGame.createAndSaveFractal;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FractalFlameAppTests {
    private final Path path = Path.of("flame.jpg");

    @BeforeEach
    public void deleteCreatedImage() throws IOException {
        Files.deleteIfExists(path);
    }

    @Test
    public void shouldCreateAndSaveFractal() throws IOException, InterruptedException {
        createAndSaveFractal(new Heart(Color.WHITE, 1), 1920, 1080);

        assertThat(Files.exists(path)).isTrue();
    }
}
