package edu.project4;

import edu.project4.Transformations.Blur;
import edu.project4.Transformations.Cylinder;
import edu.project4.Transformations.Eyefish;
import edu.project4.Transformations.Handkerchief;
import edu.project4.Transformations.Heart;
import edu.project4.Transformations.Swirl;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MemberName", "RegexpSinglelineJava"})
public class FractalFlameApp {
    private final String TRANSFORMATION_CHOICE = "Choose the transformation: enter 1 for Heart, 2 - for Eyefish, "
        + "3 - for Swirl, 4 - for Handkerchief, 5 - for Cylinder.";
    private final String RESOLUTION_CHOICE = "Choose the output file resolution: enter horizontal and vertical "
        + "resolutions in format: dddd*dddd";

    private final String GENERATING = "Generating fractal flame...";
    private final int HEART = 1;
    private final int EYEFISH = 2;
    private final int SWIRL = 3;
    private final int HANDKERCHIEF = 4;
    private final int CYLINDER = 5;

    public void run() {
        boolean isCompleted = false;
        Logger logger = LogManager.getLogger();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!isCompleted) {
                try {
                    System.out.println(TRANSFORMATION_CHOICE);
                    String transformationChoice = reader.readLine();

                    Transformation transformation = switch (Integer.parseInt(transformationChoice)) {
                        case HEART -> new Heart(Color.WHITE, 1);
                        case EYEFISH -> new Eyefish(Color.WHITE, 1);
                        case SWIRL -> new Swirl(Color.WHITE, 1);
                        case HANDKERCHIEF -> new Handkerchief(Color.WHITE, 1);
                        case CYLINDER -> new Cylinder(Color.WHITE, 1);
                        default -> new Blur(Color.WHITE, 1);
                    };

                    System.out.println(RESOLUTION_CHOICE);
                    String[] resolutions = reader.readLine().split("\\*");

                    int xRes = Integer.parseInt(resolutions[0]);
                    int yRes = Integer.parseInt(resolutions[1]);

                    System.out.println(GENERATING);

                    ChaosGame.createAndSaveFractal(transformation, xRes, yRes);

                    isCompleted = true;
                } catch (Exception e) {
                    System.out.println("Try again");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
}
