package edu.project2.generate;

import edu.project2.entity.Cell;
import edu.project2.entity.Maze;
import edu.project2.entity.Shift;
import java.util.List;
import java.util.Random;
import static edu.project2.entity.Cell.Type.PASSAGE;
import static edu.project2.entity.Cell.Type.WALL;

public class BinaryTreeAlgorithm implements Generator {
    private final List<Shift> shifts = List.of(
        new Shift(1, 0),
        new Shift(0, 1)
    );

    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        Shift shift;
        int adjCellRow;
        int adjCellColumn;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                grid[i][j] = new Cell(i, j, WALL, false);
            }
        }

        for (int i = 1; i < height; i += 2) {
            for (int j = 1; j < width; j += 2) {
                if (i == height - 2 && j == width - 2) {
                    grid[i][j] = new Cell(i, j, PASSAGE, true);
                    break;
                } else if (i == height - 2) {
                    shift = shifts.getLast();
                } else if (j == width - 2) {
                    shift = shifts.getFirst();
                } else {
                    shift = shifts.get(random.nextInt(shifts.size()));
                }

                adjCellRow = i + shift.x();
                adjCellColumn = j + shift.y();
                grid[i][j] = new Cell(i, j, PASSAGE, true);
                grid[adjCellRow][adjCellColumn] = new Cell(adjCellRow, adjCellColumn, PASSAGE, true);
            }
        }

        return new Maze(height, width, grid);
    }
}
