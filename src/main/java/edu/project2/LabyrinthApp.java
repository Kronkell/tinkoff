package edu.project2;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.generate.BinaryTreeAlgorithm;
import edu.project2.generate.Generator;
import edu.project2.generate.GrowingTreeAlgorithm;
import edu.project2.print.PrettyPrintRenderer;
import edu.project2.solve.BreadthFirstSearch;
import edu.project2.solve.DepthFirstSearch;
import edu.project2.solve.Solver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber", "MultipleStringLiterals"})
public class LabyrinthApp {
    private LabyrinthApp() {
    }

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static void run() throws IOException {
        System.out.println();
        System.out.println(ANSI_PURPLE + GREETINGS);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Maze maze;
            Generator generator;
            Solver solver;

            List<Integer> dimensions = getDimensions(reader);
            int height = dimensions.getFirst();
            int width = dimensions.getLast();

            int type = getType(reader, GENERATOR_CHOICE);
            if (type == 2) {
                generator = new GrowingTreeAlgorithm();
                maze = generator.generate(height + 1, width + 1);
            } else {
                generator = new BinaryTreeAlgorithm();
                maze = generator.generate(height + (1 - height % 2), width + (1 - width % 2));
            }

            PrettyPrintRenderer render = new PrettyPrintRenderer();
            String output = render.render(maze);
            System.out.println(output);

            System.out.println(ANSI_PURPLE + LIST_OF_COORDS_INFO + ANSI_RESET);
            List<Coordinate> passageCells = render.getPassageCells(maze);
            System.out.println(printPassageCells(passageCells));

            Coordinate start = null;
            Coordinate finish;
            List<Coordinate> solution = null;
            boolean isSolutionPresent = false;
            while (!isSolutionPresent) {
                start = getCoordinate(reader, "start", passageCells);
                finish = getCoordinate(reader, "finish", passageCells);

                type = getType(reader, SOLVER_CHOICE);
                if (type == 1) {
                    solver = new DepthFirstSearch();
                } else {
                    solver = new BreadthFirstSearch();
                }

                solution = solver.solve(
                    maze,
                    new Coordinate(
                        start.row() + render.getIsTopBorderThick(),
                        start.column() + render.getIsLeftBorderThick()
                    ),
                    new Coordinate(
                        finish.row() + render.getIsTopBorderThick(),
                        finish.column() + render.getIsLeftBorderThick()
                    )
                );

                if (!solution.isEmpty()) {
                    isSolutionPresent = true;
                } else {
                    System.out.println(NO_PATH_INFO);
                }
            }

            output = render.renderWithPath(
                maze,
                solution,
                new Coordinate(
                    start.row() + render.getIsTopBorderThick(),
                    start.column() + render.getIsLeftBorderThick()
                )
            );
            System.out.println(output);
        }

    }

    private static List<Integer> getDimensions(BufferedReader reader) {
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

        return List.of(height, width);
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
        } while (type != 1 && type != 2);

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
        return (height >= 3 && height < 500 && width >= 3 && width < 500);
    }

    private static boolean isCoordinateValid(int x, int y, List<Coordinate> coordinates) {
        return coordinates.contains(new Coordinate(x, y));
    }

    public static final String GREETINGS = """
        Welcome to the Labyrinth app! It can generate a maze of desired size and quickly find path between 2 points
        if it exists.

        For the best user experience, please set "Line height" setting to 1.0.
        """;
    public static final String DIMENSION_RULES = "Please, enter the maze dimensions, separated by space. "
        + "Notice that the numbers must be bigger than 2 and smaller than 500";

    public static final String GENERATOR_CHOICE =
        "Please, choose the algorithm to generate a maze. Type \"1\" for Binary Tree Algorithm."
            + " Type \"2\" for Growing Tree Algorithm";
    public static final String LIST_OF_COORDS_INFO =
        "Here is the list of coordinates (counting starts at 0) you can choose as Start or Finish. Choose wisely ;)";

    public static final String SOLVER_CHOICE = "Please, choose the algorithm to solve the maze. "
        + "Type \"1\" for the classic Depth-First Search. Type \"2\" for Breadth-First Search";
    public static final String NO_PATH_INFO = "No path between the 2 cells, sadly.";

    public static final String START_RULES = "Please, enter the start coordinates, separated by space. "
        + "Notice that the coordinate must be one of the possible coordinates from the list";

    public static final String FINISH_RULES = "Please, enter the finish coordinates, separated by space. "
        + "Notice that the coordinate must be one of the possible coordinates from the list";

}
