package edu.project2;

import java.util.HashSet;
import java.util.List;
import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.solve.BreadthFirstSearch;
import edu.project2.solve.DepthFirstSearch;
import org.junit.jupiter.api.Test;
import static edu.project2.entity.Cell.Type.PASSAGE;
import static edu.project2.entity.Cell.Type.WALL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SolverTest {
    @Test
    public void solveMazeByBFSGivenMaze() {
        Maze maze = new Maze(4, 4, new Cell[][] {
            new Cell[] {
                new Cell(0, 0, WALL, false),
                new Cell(0, 1, WALL, false),
                new Cell(0, 2, WALL, false),
                new Cell(0, 3, WALL, false),
                new Cell(0, 4, WALL, false)
            },
            new Cell[] {
                new Cell(1, 0, WALL, false),
                new Cell(1, 1, PASSAGE, true),
                new Cell(1, 2, WALL, false),
                new Cell(1, 3, PASSAGE, true),
                new Cell(1, 4, WALL, false)
            },
            new Cell[] {
                new Cell(2, 0, WALL, false),
                new Cell(2, 1, PASSAGE, true),
                new Cell(2, 2, WALL, false),
                new Cell(2, 3, PASSAGE, true),
                new Cell(2, 4, WALL, false)
            },
            new Cell[] {
                new Cell(3, 0, WALL, false),
                new Cell(3, 1, PASSAGE, true),
                new Cell(3, 2, PASSAGE, true),
                new Cell(3, 3, PASSAGE, true),
                new Cell(3, 4, WALL, false)
            },
            new Cell[] {
                new Cell(4, 0, WALL, false),
                new Cell(4, 1, WALL, false),
                new Cell(4, 2, WALL, false),
                new Cell(4, 3, WALL, false),
                new Cell(4, 4, WALL, false)
            }
        });
        List<Coordinate> expectedSolution = List.of(
            new Coordinate(1,1),
            new Coordinate(2,1),
            new Coordinate(3,1),
            new Coordinate(3,2),
            new Coordinate(3,3),
            new Coordinate(2,3),
            new Coordinate(1,3)
        );

        BreadthFirstSearch solver = new BreadthFirstSearch();
        List<Coordinate> actualSolution = solver.solve(
            maze,
            new Coordinate(1,1),
            new Coordinate(1, 3)
        );

        assertThat(new HashSet<>(actualSolution))
            .isEqualTo(new HashSet<>(expectedSolution));
    }

    @Test
    public void solveMazeByDFSGivenMaze() {
        Maze maze = new Maze(4, 4, new Cell[][] {
            new Cell[] {
                new Cell(0, 0, WALL, false),
                new Cell(0, 1, WALL, false),
                new Cell(0, 2, WALL, false),
                new Cell(0, 3, WALL, false),
                new Cell(0, 4, WALL, false)
            },
            new Cell[] {
                new Cell(1, 0, WALL, false),
                new Cell(1, 1, PASSAGE, true),
                new Cell(1, 2, WALL, false),
                new Cell(1, 3, PASSAGE, true),
                new Cell(1, 4, WALL, false)
            },
            new Cell[] {
                new Cell(2, 0, WALL, false),
                new Cell(2, 1, PASSAGE, true),
                new Cell(2, 2, WALL, false),
                new Cell(2, 3, PASSAGE, true),
                new Cell(2, 4, WALL, false)
            },
            new Cell[] {
                new Cell(3, 0, WALL, false),
                new Cell(3, 1, PASSAGE, true),
                new Cell(3, 2, PASSAGE, true),
                new Cell(3, 3, PASSAGE, true),
                new Cell(3, 4, WALL, false)
            },
            new Cell[] {
                new Cell(4, 0, WALL, false),
                new Cell(4, 1, WALL, false),
                new Cell(4, 2, WALL, false),
                new Cell(4, 3, WALL, false),
                new Cell(4, 4, WALL, false)
            }
        });
        List<Coordinate> expectedSolution = List.of(
            new Coordinate(1,1),
            new Coordinate(2,1),
            new Coordinate(3,1),
            new Coordinate(3,2),
            new Coordinate(3,3),
            new Coordinate(2,3),
            new Coordinate(1,3)
        );

        DepthFirstSearch solver = new DepthFirstSearch();
        List<Coordinate> actualSolution = solver.solve(
            maze,
            new Coordinate(1,1),
            new Coordinate(1, 3)
        );

        assertThat(new HashSet<>(actualSolution))
            .isEqualTo(new HashSet<>(expectedSolution));
    }
}
