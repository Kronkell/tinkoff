package edu.project2;

@SuppressWarnings("MultipleStringLiterals")
public class StringConstants {
    private StringConstants() {

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
