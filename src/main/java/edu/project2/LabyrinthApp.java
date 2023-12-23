package edu.project2;

import edu.project2.entity.Coordinate;
import edu.project2.entity.DimensionPair;
import edu.project2.entity.Maze;
import edu.project2.generate.BinaryTreeAlgorithm;
import edu.project2.generate.GrowingTreeAlgorithm;
import edu.project2.print.PrettyPrintRenderer;
import edu.project2.solve.BreadthFirstSearch;
import edu.project2.solve.DepthFirstSearch;
import edu.project2.solve.Solver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import static edu.project2.StringConstants.DIMENSION_RULES;
import static edu.project2.StringConstants.FINISH_RULES;
import static edu.project2.StringConstants.GENERATOR_CHOICE;
import static edu.project2.StringConstants.GREETINGS;
import static edu.project2.StringConstants.LIST_OF_COORDS_INFO;
import static edu.project2.StringConstants.NO_PATH_INFO;
import static edu.project2.StringConstants.SOLVER_CHOICE;
import static edu.project2.StringConstants.START_RULES;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber", "MultipleStringLiterals"})
public class LabyrinthApp {
    private LabyrinthApp() {
    }

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final int FIRST_TYPE = 1;
    private static final int SECOND_TYPE = 2;
    private static final int MAX_SIZE = 500;
    private static final int MIN_SIZE = 3;

    public static void run() throws IOException {
        System.out.println();
        System.out.println(ANSI_PURPLE + GREETINGS);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Maze maze = generateMaze(reader);

            PrettyPrintRenderer render = getRenderAndPrintMaze(maze);

            List<Coordinate> passageCells = getAndPrintPassageCells(render, maze);

            solveAndPrintMaze(maze, reader, passageCells, render);
        }
    }

    private static Maze generateMaze(BufferedReader reader) {
        DimensionPair dimensions = getDimensions(reader);
        int height = dimensions.height();
        int width = dimensions.width();

        int type = getType(reader, GENERATOR_CHOICE);
        if (type == SECOND_TYPE) {
            return new GrowingTreeAlgorithm().generate(height + 1, width + 1);
        } else {
            return new BinaryTreeAlgorithm().generate(height + (1 - height % 2), width + (1 - width % 2));
        }
    }

    private static PrettyPrintRenderer getRenderAndPrintMaze(Maze maze) {
        PrettyPrintRenderer render = new PrettyPrintRenderer();
        String output = render.render(maze);
        System.out.println(output);
        return render;
    }

    private static List<Coordinate> getAndPrintPassageCells(PrettyPrintRenderer render, Maze maze) {
        System.out.println(ANSI_PURPLE + LIST_OF_COORDS_INFO + ANSI_RESET);
        List<Coordinate> passageCells = render.getPassageCells(maze);
        System.out.println(printPassageCells(passageCells));
        return passageCells;
    }

    private static void solveAndPrintMaze(
        Maze maze,
        BufferedReader reader,
        List<Coordinate> passageCells,
        PrettyPrintRenderer render
    ) {
        Coordinate start = null;
        Coordinate finish;
        List<Coordinate> solution = null;
        boolean isSolutionPresent = false;
        int topOffset = render.getIsTopBorderThick() ? 1 : 0;
        int leftOffset = render.getIsLeftBorderThick() ? 1 : 0;
        while (!isSolutionPresent) {
            start = getCoordinate(reader, "start", passageCells);
            finish = getCoordinate(reader, "finish", passageCells);

            int type = getType(reader, SOLVER_CHOICE);
            Solver solver;
            if (type == FIRST_TYPE) {
                solver = new DepthFirstSearch();
            } else {
                solver = new BreadthFirstSearch();
            }

            solution = solver.solve(
                maze,
                new Coordinate(
                    start.row() + topOffset,
                    start.column() + leftOffset
                ),
                new Coordinate(
                    finish.row() + topOffset,
                    finish.column() + leftOffset
                )
            );

            if (!solution.isEmpty()) {
                isSolutionPresent = true;
            } else {
                System.out.println(NO_PATH_INFO);
            }
        }

        System.out.println(render.renderWithPath(
            maze,
            solution,
            new Coordinate(
                start.row() + topOffset,
                start.column() + leftOffset
            )
        ));
    }

    private static DimensionPair getDimensions(BufferedReader reader) {
        int height = 0;
        int width = 0;
        String input;
        do {
            try {
                System.out.println(ANSI_BLUE + DIMENSION_RULES + ANSI_RESET);
                input = reader.readLine();
                height = Integer.parseInt(input.split(" ")[0]);
                width = Integer.parseInt(input.split(" ")[1]);
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Wrong input!" + ANSI_RESET);
            }
        } while (!areDimensionsValid(height, width));

        return new DimensionPair(height, width);
    }

    private static Integer getType(BufferedReader reader, String message) {
        int type = 0;
        String input;
        do {
            try {
                System.out.println(ANSI_BLUE + message + ANSI_RESET);
                input = reader.readLine();
                type = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Wrong input!" + ANSI_RESET);
            }
        } while (type != FIRST_TYPE && type != SECOND_TYPE);

        return type;
    }

    private static Coordinate getCoordinate(BufferedReader reader, String type, List<Coordinate> coordinates) {
        int x = 0;
        int y = 0;
        String input;
        do {
            try {
                if (type.equals("start")) {
                    System.out.println(START_RULES);
                } else {
                    System.out.println(FINISH_RULES);
                }
                input = reader.readLine();
                if (input.split(" ").length != 2) {
                    continue;
                }
                x = Integer.parseInt(input.split(" ")[0]);
                y = Integer.parseInt(input.split(" ")[1]);
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Wrong input!" + ANSI_RESET);
            }
        } while (!isCoordinateValid(x, y, coordinates));

        return new Coordinate(x, y);
    }

    private static String printPassageCells(List<Coordinate> passageCells) {
        final int maxLineLength = 23;
        int counter = 0;
        StringBuilder builder = new StringBuilder();
        for (var coordinate : passageCells) {
            if (counter > maxLineLength) {
                builder.append("\n");
                counter = 0;
            }
            builder.append("(").append(coordinate.row()).append(",").append(coordinate.column()).append(") ");
            counter++;
        }

        return builder.toString();
    }

    private static boolean areDimensionsValid(int height, int width) {
        return (height >= MIN_SIZE && height < MAX_SIZE && width >= MIN_SIZE && width < MAX_SIZE);
    }

    private static boolean isCoordinateValid(int x, int y, List<Coordinate> coordinates) {
        return coordinates.contains(new Coordinate(x, y));
    }

}
