package edu.project4;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import static edu.project4.ImageUtils.save;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.StrictMath.log10;

@SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
public class ChaosGame {
    private ChaosGame() {
    }

    private static final int COLOR_MAX = 255;
    private static final int MULT_UP_BOUND = 2;
    private static final int MULT_LOW_BOUND = -2;
    private static final double GAMMA = 2.2;

    static private void render(
        FractalImage canvas,
        List<AffineTransformation> affineTransformations,
        List<? extends Transformation> variations,
        int symmetry,
        long n
    ) {
        double x = ThreadLocalRandom.current().nextDouble(-1, 1);
        double y = ThreadLocalRandom.current().nextDouble(-1, 1);
        int xRes = canvas.width();
        int yRes = canvas.height();
        double xMax = MULT_UP_BOUND;
        double xMin = MULT_LOW_BOUND;
        double yMax = MULT_UP_BOUND;
        double yMin = MULT_LOW_BOUND;

        Point p = new Point(x, y);

        double[] c = {
            ThreadLocalRandom.current().nextDouble(),
            ThreadLocalRandom.current().nextDouble(),
            ThreadLocalRandom.current().nextDouble()
        };

        for (long i = -20; i < n; ++i) {
            int affineTransformIndex = ThreadLocalRandom.current().nextInt(affineTransformations.size());
            AffineTransformation affineTransform = affineTransformations.get(affineTransformIndex);

            p = affineTransform.apply(p);

            double newX = 0;
            double newY = 0;
            for (Transformation transform : variations) {
                newX += transform.getWeight() * transform.apply(p).x();
                newY += transform.getWeight() * transform.apply(p).y();
            }

            p = new Point(newX, newY);

            /*affineTransformIndex = ThreadLocalRandom.current().nextInt(affineTransformations.size());
            p = affineTransformations.get(affineTransformIndex).apply(p);*/

            if (i >= 0) {
                double theta = 0.0;
                for (int s = 0; s < symmetry; ++s) {
                    theta += ((2 * PI) / symmetry);
                    newX = p.x() * cos(theta) - p.y() * sin(theta);
                    newY = p.x() * sin(theta) + p.y() * cos(theta);

                    p = new Point(newX, newY);
                    int x1 = (int) abs(xRes - Math.floor((xMax - p.x()) / (xMax - xMin) * xRes));
                    int y1 = (int) abs(yRes - Math.floor((yMax - p.y()) / (yMax - yMin) * yRes));

                    if (x1 > 0 && x1 < xRes && y1 > 0 && y1 < yRes) {
                        synchronized (canvas.data()[x1][y1]) {
                            Pixel currentPixel = canvas.data()[x1][y1];
                            if (currentPixel.getHitCount() == 0) {
                                canvas.data()[x1][y1] = new Pixel(affineTransform.getColor(), 1, 0);
                            } else {
                                c[0] = (c[0] + affineTransform.r()) / 2;
                                c[1] = (c[1] + affineTransform.g()) / 2;
                                c[2] = (c[2] + affineTransform.blue()) / 2;

                                int colorsave = (int) ((currentPixel.getColor().getRed() + c[0] * COLOR_MAX) / 2);
                                int colorsave1 = (int) ((currentPixel.getColor().getGreen() + c[1] * COLOR_MAX) / 2);
                                int colorsave2 = (int) ((currentPixel.getColor().getBlue() + c[2] * COLOR_MAX) / 2);

                                canvas.data()[x1][y1].setColor(new Color(
                                    colorsave,
                                    colorsave1,
                                    colorsave2
                                ));

                                canvas.data()[x1][y1].setHitCount(currentPixel.getHitCount() + 1);
                            }
                        }
                    }
                }
            }
        }

    }

    private static void logScale(FractalImage image, double gamma) {
        double maxFreq = 0;
        for (int i = 0; i < image.width(); ++i) {
            for (int j = 0; j < image.height(); ++j) {
                Pixel pixel = image.data()[i][j];
                if (pixel.getHitCount() > 0) {
                    image.data()[i][j].setNormal(log10(pixel.getHitCount()));
                    maxFreq = max(maxFreq, pixel.getHitCount());
                }
            }
        }
        double alpha;
        double maxColor = 0.0;
        for (int i = 0; i < image.width(); ++i) {
            for (int j = 0; j < image.height(); ++j) {
                Pixel pixel = image.data()[i][j];
                if (pixel.getHitCount() > 0) {
                    alpha = log10(pixel.getHitCount()) / log10(maxFreq);
                    double red = (pixel.getColor().getRed() * pow(alpha, 1.0 / gamma));
                    double green = (pixel.getColor().getGreen() * pow(alpha, 1.0 / gamma));
                    double blue = (pixel.getColor().getBlue() * pow(alpha, 1.0 / gamma));
                    image.data()[i][j].setColor(new Color((int) red, (int) green, (int) blue));
                    maxColor = max(maxColor, red);
                    maxColor = max(maxColor, green);
                    maxColor = max(maxColor, blue);
                }
            }
        }
    }

    private static AffineTransformation randomAffineTransformation() {

        boolean isRight = false;
        double a = 0;
        double b = 0;
        double d = 0;
        double e = 0;
        while (!isRight) {
            a = ThreadLocalRandom.current().nextDouble(-1, 1);
            b = ThreadLocalRandom.current().nextDouble(-1, 1);
            d = ThreadLocalRandom.current().nextDouble(-1, 1);
            e = ThreadLocalRandom.current().nextDouble(-1, 1);
            if (a * a + d * d < 1
                && b * b + e * e < 1
                && a * a + b * b + e * e + d * d < 1 + (a * e - b * d) * (a * e - b * d)) {
                isRight = true;
            }
        }

        double c = ThreadLocalRandom.current().nextDouble(-1, 1);
        double f = ThreadLocalRandom.current().nextDouble(-1, 1);

        float r = ThreadLocalRandom.current().nextFloat();
        float g = ThreadLocalRandom.current().nextFloat();
        float blue = ThreadLocalRandom.current().nextFloat();

        return new AffineTransformation(a, b, c, d, e, f, new Color(r, g, blue), r, g, blue);
    }

    public static void createAndSaveFractal(Transformation transformation, int xRes, int yRes)
        throws IOException, InterruptedException {
        Pixel[][] data = new Pixel[xRes][yRes];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                data[i][j] = new Pixel(Color.black, 0, 0);
            }
        }
        FractalImage fractalImage = new FractalImage(data, xRes, yRes);
        var affineTransformations = List.of(
            randomAffineTransformation(),
            randomAffineTransformation(),
            randomAffineTransformation(),
            randomAffineTransformation(),
            randomAffineTransformation(),
            randomAffineTransformation(),
            randomAffineTransformation(),
            randomAffineTransformation(),
            randomAffineTransformation(),
            randomAffineTransformation()
        );

        var vars = List.of(transformation);

        long then = System.nanoTime();
        int iterNumber = 10;
        int n = 100_000_000;
        int symmetry = 1;

        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch latch = new CountDownLatch(iterNumber);
        for (int i = 0; i < iterNumber; ++i) {
            pool.execute(new RenderWorker(fractalImage, affineTransformations, vars, symmetry, n, latch));
        }

        latch.await();
        pool.shutdown();

        logScale(fractalImage, GAMMA);

        save(fractalImage, Path.of("flame"), ImageFormat.JPG);

        System.out.println("Elapsed time is " + (System.nanoTime() - then) / 1e9 + "s");
    }

    static class RenderWorker implements Runnable {
        final FractalImage fractalImage;
        final List<AffineTransformation> affineTransformations;
        final List<? extends Transformation> variations;
        final int symmetry;
        final long n;
        CountDownLatch latch;

        private RenderWorker(
            FractalImage fractalImage,
            List<AffineTransformation> affineTransformations,
            List<? extends Transformation> variations,
            int symmetry,
            long n,
            CountDownLatch latch
        ) {
            this.fractalImage = fractalImage;
            this.affineTransformations = affineTransformations;
            this.variations = variations;
            this.symmetry = symmetry;
            this.n = n;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                render(fractalImage, affineTransformations, variations, symmetry, n);
            } catch (Throwable t) {
                System.out.println("Rendering error");
            } finally {
                latch.countDown();
            }
        }
    }
}
