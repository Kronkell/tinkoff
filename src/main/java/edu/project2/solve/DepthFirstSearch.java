package edu.project2.solve;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.entity.Shift;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static edu.project2.entity.Cell.Type.PASSAGE;

public class DepthFirstSearch implements Solver {
    private final List<Shift> shifts = List.of(
        new Shift(1, 0),
        new Shift(0, 1),
        new Shift(-1, 0),
        new Shift(0, -1)
    );
    private boolean[][] was;
    private boolean isSolutionFound = false;
    private final List<Coordinate> solution = new ArrayList<>();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        was = new boolean[maze.getGrid().length][maze.getGrid()[0].length];
        Arrays.stream(was).forEach(arr -> Arrays.fill(arr, false));

        dfs(start, maze.getGrid(), end);

        return solution;
    }

    @SuppressWarnings("ReturnCount")
    private void dfs(Coordinate c, Cell[][] grid, Coordinate finish) {
        if (was[c.row()][c.column()]) {
            return;
        }

        if (c.equals(finish)) {
            isSolutionFound = true;
            solution.add(c);
            return;
        }

        was[c.row()][c.column()] = true;

        for (Shift shift : shifts) {
            int newRow = c.row() + shift.x();
            int newColumn = c.column() + shift.y();
            if (isInside(newRow, newColumn, grid.length, grid[0].length)
                && grid[newRow][newColumn].type() == PASSAGE) {

                dfs(new Coordinate(newRow, newColumn), grid, finish);

                if (isSolutionFound) {
                    solution.add(c);
                    return;
                }
            }
        }
    }

    private boolean isInside(int row, int column, int height, int width) {
        return row >= 0 && row < height && column >= 0 && column < width;
    }
}
