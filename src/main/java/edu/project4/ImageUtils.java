package edu.project4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
    }

    static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.width(); ++i) {
            for (int j = 0; j < image.height(); ++j) {
                bufferedImage.setRGB(i, j, image.data()[i][j].getColor().getRGB());
            }
        }

        File imageFile = new File(String.valueOf(filename) + '.' + format.name().toLowerCase());
        ImageIO.write(bufferedImage, format.name().toLowerCase(), imageFile);
    }
}
