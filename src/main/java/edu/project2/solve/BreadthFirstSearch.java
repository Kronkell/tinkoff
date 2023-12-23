package edu.project2.solve;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.entity.Shift;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import static edu.project2.entity.Cell.Type.PASSAGE;

public class BreadthFirstSearch implements Solver {
    private final List<Shift> shifts = List.of(
        new Shift(1, 0),
        new Shift(0, 1),
        new Shift(-1, 0),
        new Shift(0, -1)
    );

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (start.equals(end)) {
            return List.of(start);
        }

        final int maxValue = 100000;
        final Cell[][] grid = maze.getGrid();
        Deque<Coordinate> cellDeque = new ArrayDeque<>();
        List<Coordinate> path = new ArrayList<>();
        int[][] distance = new int[grid.length][grid[0].length];
        Coordinate[][] predecessor = new Coordinate[grid.length][grid[0].length];
        Coordinate currentCell;
        int currentRow;
        int currentColumn;
        int newRow;
        int newColumn;
        int dist;

        Arrays.stream(distance).forEach(arr -> Arrays.fill(arr, maxValue));
        distance[start.row()][start.column()] = 0;
        cellDeque.add(start);

        while (!cellDeque.isEmpty()) {
            currentCell = cellDeque.pollFirst();

            for (Shift shift : shifts) {
                currentRow = currentCell.row();
                currentColumn = currentCell.column();
                newRow = currentRow + shift.x();
                newColumn = currentColumn + shift.y();
                if (isInside(newRow, newColumn, grid.length, grid[0].length)
                    && grid[newRow][newColumn].type() == PASSAGE) {

                    if (distance[newRow][newColumn] == maxValue) {
                        distance[newRow][newColumn] = distance[currentRow][currentColumn] + 1;
                        predecessor[newRow][newColumn] = new Coordinate(currentRow, currentColumn);
                        cellDeque.add(new Coordinate(newRow, newColumn));
                    }
                }
            }
        }

        path.add(start);
        currentRow = end.row();
        currentColumn = end.column();
        Coordinate pred;
        dist = distance[end.row()][end.column()];
        if (dist != maxValue) {
            while (dist-- > 0) {
                path.add(new Coordinate(currentRow, currentColumn));
                if (dist > 0) {
                    pred = predecessor[currentRow][currentColumn];
                    currentRow = pred.row();
                    currentColumn = pred.column();
                }
            }
        }

        return path;
    }

    private boolean isInside(int row, int column, int height, int width) {
        return row >= 0 && row < height && column >= 0 && column < width;
    }
}
