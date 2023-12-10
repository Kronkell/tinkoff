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

@SuppressWarnings({"MemberName", "RegexpSinglelineJava"})
public class FractalFlameApp {
    private final String TRANSFORMATION_CHOICE = "Choose the transformation: enter 1 for Heart, 2 - for Eyefish, "
        + "3 - for Swirl, 4 - for Handkerchief, 5 - for Cylinder.";
    private final String RESOLUTION_CHOICE = "Choose the output file resolution: enter horizontal and vertical "
        + "resolutions in format: dddd*dddd";

    private final String GENERATING = "Generating fractal flame...";
    private final int ONE = 1;
    private final int TWO = 2;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIVE = 5;

    public void run() {
        boolean isCompleted = false;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!isCompleted) {
                try {
                    System.out.println(TRANSFORMATION_CHOICE);
                    String transformationChoice = reader.readLine();

                    Transformation transformation = switch (Integer.parseInt(transformationChoice)) {
                        case ONE -> new Heart(Color.WHITE, 1);
                        case TWO -> new Eyefish(Color.WHITE, 1);
                        case THREE -> new Swirl(Color.WHITE, 1);
                        case FOUR -> new Handkerchief(Color.WHITE, 1);
                        case FIVE -> new Cylinder(Color.WHITE, 1);
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
            System.out.println(e.getMessage());
        }

    }
}
