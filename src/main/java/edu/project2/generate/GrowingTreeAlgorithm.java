package edu.project2.generate;

import edu.project2.entity.Cell;
import edu.project2.entity.CellPair;
import edu.project2.entity.Maze;
import edu.project2.entity.Shift;
import edu.project2.entity.ShiftPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static edu.project2.entity.Cell.Type.PASSAGE;
import static edu.project2.entity.Cell.Type.WALL;

public class GrowingTreeAlgorithm implements Generator {
    private final List<ShiftPair> shifts = List.of(
        new ShiftPair(new Shift(2, 0), new Shift(1, 0)),
        new ShiftPair(new Shift(0, 2), new Shift(0, 1)),
        new ShiftPair(new Shift(-2, 0), new Shift(-1, 0)),
        new ShiftPair(new Shift(0, -2), new Shift(0, -1))
    );
    private final Random random = new Random();

    @SuppressWarnings("MagicNumber")
    @Override
    public Maze generate(int height, int width) {
        int attemptCount = 30;
        List<Cell> cellList = new ArrayList<>();
        Cell[][] grid = new Cell[height][width];
        Cell currentCell;
        Cell newCell;
        Cell adjCell;
        CellPair currentPair;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                grid[i][j] = new Cell(i, j, WALL, false);
            }
        }

        cellList.add(new Cell(
            random.nextInt(height - 2) + 1,
            random.nextInt(width - 2) + 1,
            PASSAGE,
            true
        ));

        while (!cellList.isEmpty()) {
            currentCell = cellList.getLast();
            grid[currentCell.row()][currentCell.column()] = currentCell;

            if (isThereUnvisitedNeighbors(currentCell, grid)) {
                currentPair = getRandomUnvisitedNeighborCell(currentCell, grid, attemptCount);
                newCell = currentPair.first();
                adjCell = currentPair.second();
                cellList.add(newCell);
                grid[adjCell.row()][adjCell.column()] = adjCell;
            } else {
                cellList.remove(currentCell);
            }
        }

        return new Maze(height, width, grid);
    }

    private boolean isInside(int row, int column, int height, int width) {
        return row > 0 && row < height - 1 && column > 0 && column < width - 1;
    }

    private boolean isThereUnvisitedNeighbors(Cell currentCell, Cell[][] grid) {
        for (ShiftPair shift : shifts) {
            int newCellRow = currentCell.row() + shift.first().x();
            int newCellColumn = currentCell.column() + shift.first().y();
            if (isInside(newCellRow, newCellColumn, grid.length, grid[0].length)
                && !grid[newCellRow][newCellColumn].visited()) {

                return true;
            }
        }

        return false;
    }

    private CellPair getRandomUnvisitedNeighborCell(Cell currentCell, Cell[][] grid, int attemptCount) {
        int i = attemptCount;
        ShiftPair shift;
        CellPair answer = new CellPair(
            new Cell(0, 0, WALL, false),
            new Cell(0, 0, WALL, false)
        );
        int newCellRow;
        int newCellColumn;
        int adjCellRow;
        int adjCellColumn;

        while (i-- > 0) {
            shift = shifts.get(random.nextInt(shifts.size()));

            newCellRow = currentCell.row() + shift.first().x();
            newCellColumn = currentCell.column() + shift.first().y();
            adjCellRow = currentCell.row() + shift.second().x();
            adjCellColumn = currentCell.column() + shift.second().y();

            if (isInside(newCellRow, newCellColumn, grid.length, grid[0].length)
                && !grid[newCellRow][newCellColumn].visited()) {

                return new CellPair(
                    new Cell(newCellRow, newCellColumn, PASSAGE, true),
                    new Cell(adjCellRow, adjCellColumn, PASSAGE, true)
                );
            }
        }

        for (ShiftPair sh : shifts) {
            newCellRow = currentCell.row() + sh.first().x();
            newCellColumn = currentCell.column() + sh.first().y();
            adjCellRow = currentCell.row() + sh.second().x();
            adjCellColumn = currentCell.column() + sh.second().x();

            if (isInside(newCellRow, newCellColumn, grid.length, grid[0].length)
                && !grid[newCellRow][newCellColumn].visited()) {

                return new CellPair(
                    new Cell(newCellRow, newCellColumn, PASSAGE, true),
                    new Cell(adjCellRow, adjCellColumn, PASSAGE, true)
                );
            }
        }

        return answer;
    }

}
