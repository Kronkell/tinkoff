package edu.project2.print;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.InfoTuple;
import edu.project2.entity.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static edu.project2.entity.Cell.Type.PASSAGE;
import static edu.project2.entity.Cell.Type.WALL;

public class PrettyPrintRenderer implements Renderer {
    private static final char BLOCK = '█';
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private boolean isLeftBorderThick;
    private boolean isTopBorderThick;

    @Override
    public String render(Maze maze) {
        StringBuilder ans = new StringBuilder();
        InfoTuple infoTuple = deleteUselessBorders(maze.getGrid());
        Cell[][] newGrid = infoTuple.grid();
        isLeftBorderThick = infoTuple.isLeftBorderThick();
        isTopBorderThick = infoTuple.isTopBorderThick();
        for (Cell[] cells : newGrid) {
            for (Cell currentCell : cells) {
                if (currentCell.type() == WALL) {
                    ans.append(BLOCK).append(BLOCK);
                } else if (currentCell.type() == PASSAGE && currentCell.visited()) {
                    ans.append("  ");
                }
            }
            ans.append("\n");
        }

        return ans.toString();
    }

    @Override
    public String renderWithPath(Maze maze, List<Coordinate> path, Coordinate start) {
        Cell currentCell;
        StringBuilder ans = new StringBuilder();
        InfoTuple infoTuple = deleteUselessBorders(maze.getGrid());
        Cell[][] newGrid = infoTuple.grid();
        isLeftBorderThick = infoTuple.isLeftBorderThick();
        isTopBorderThick = infoTuple.isTopBorderThick();
        for (int i = 0; i < newGrid.length; ++i) {
            for (int j = 0; j < newGrid[0].length; ++j) {
                currentCell = newGrid[i][j];
                if (i == start.row() && j == start.column()) {
                    ans.append(ANSI_GREEN).append(BLOCK).append(BLOCK).append(ANSI_RESET);
                } else if (path.contains(new Coordinate(i, j))) {
                    ans.append(ANSI_RED).append(BLOCK).append(BLOCK).append(ANSI_RESET);
                } else if (currentCell.type() == WALL) {
                    ans.append(BLOCK).append(BLOCK);
                } else if (currentCell.type() == PASSAGE && currentCell.visited()) {
                    ans.append("  ");
                }
            }
            ans.append("\n");
        }

        return ans.toString();
    }

    public List<Coordinate> getPassageCells(Maze maze) {
        List<Coordinate> passageCells = new ArrayList<>();
        Cell[][] grid = maze.getGrid();
        for (int i = isTopBorderThick ? 1 : 0; i < grid.length; ++i) {
            for (int j = isLeftBorderThick ? 1 : 0; j < grid[0].length; ++j) {
                if (grid[i][j].type() == PASSAGE && grid[i][j].visited()) {
                    passageCells.add(new Coordinate(
                        i - (isTopBorderThick ? 1 : 0),
                        j - (isLeftBorderThick ? 1 : 0)
                    ));
                }
            }
        }

        return passageCells;
    }

    public boolean getIsLeftBorderThick() {
        return isLeftBorderThick;
    }

    public boolean getIsTopBorderThick() {
        return isTopBorderThick;
    }

    @SuppressWarnings("CyclomaticComplexity")
    public InfoTuple deleteUselessBorders(Cell[][] grid) {
        Cell[][] newGrid = Arrays.stream(grid).map(Cell[]::clone).toArray(Cell[][]::new);

        boolean isBorderThick = true;
        boolean isLeftBorderThickLoc = false;
        boolean isTopBorderThickLoc = false;

        for (int j = 0; j < grid[0].length; ++j) {
            if (grid[1][j].type() == PASSAGE) {
                isBorderThick = false;
                break;
            }
        }

        if (isBorderThick) {
            isTopBorderThickLoc = true;
            for (int j = 0; j < grid[0].length; ++j) {
                newGrid[0][j] = new Cell(0, j, PASSAGE, false);
            }
        }
        isBorderThick = true;

        for (int j = 0; j < grid[0].length; ++j) {
            if (grid[grid.length - 2][j].type() == PASSAGE) {
                isBorderThick = false;
                break;
            }
        }

        if (isBorderThick) {
            for (int j = 0; j < grid[0].length; ++j) {
                newGrid[grid.length - 1][j] = new Cell(grid.length - 1, j, PASSAGE, false);
            }
        }
        isBorderThick = true;

        for (Cell[] value : grid) {
            if (value[1].type() == PASSAGE) {
                isBorderThick = false;
                break;
            }
        }

        if (isBorderThick) {
            isLeftBorderThickLoc = true;
            for (int i = 0; i < grid.length; ++i) {
                newGrid[i][0] = new Cell(i, 0, PASSAGE, false);
            }
        }
        isBorderThick = true;

        for (Cell[] cells : grid) {
            if (cells[grid[0].length - 2].type() == PASSAGE) {
                isBorderThick = false;
                break;
            }
        }

        if (isBorderThick) {
            for (int i = 0; i < grid.length; ++i) {
                newGrid[i][grid[0].length - 1] = new Cell(i, grid[0].length - 1, PASSAGE, false);
            }
        }

        return new InfoTuple(newGrid, isLeftBorderThickLoc, isTopBorderThickLoc);
    }
}
