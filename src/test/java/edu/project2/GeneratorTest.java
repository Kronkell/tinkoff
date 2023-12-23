package edu.project2;

import edu.project2.entity.Cell;
import edu.project2.entity.Maze;
import edu.project2.generate.BinaryTreeAlgorithm;
import edu.project2.generate.GrowingTreeAlgorithm;
import org.junit.jupiter.api.Test;
import static edu.project2.entity.Cell.Type.PASSAGE;
import static edu.project2.entity.Cell.Type.WALL;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GeneratorTest {
    @Test
    public void generateMazeByGrowingTreeGivenDimensions() {
        int height = 3;
        int width = 3;
        Maze expectedMaze = new Maze(3, 3, new Cell[][] {
            new Cell[] {
                new Cell(0, 0, WALL, false),
                new Cell(0, 1, WALL, false),
                new Cell(0, 2, WALL, false)
            },
            new Cell[] {
                new Cell(1, 0, WALL, false),
                new Cell(1, 1, PASSAGE, true),
                new Cell(1, 2, WALL, false)
            },
            new Cell[] {
                new Cell(2, 0, WALL, false),
                new Cell(2, 1, WALL, false),
                new Cell(2, 2, WALL, false)
            }
        });

        GrowingTreeAlgorithm alg = new GrowingTreeAlgorithm();
        Maze actualMaze = alg.generate(height, width);

        assertArrayEquals(actualMaze.getGrid(), expectedMaze.getGrid());
    }

    @Test
    public void generateMazeByBinaryTreeGivenDimensions() {
        int height = 3;
        int width = 3;
        Maze expectedMaze = new Maze(3, 3, new Cell[][] {
            new Cell[] {
                new Cell(0, 0, WALL, false),
                new Cell(0, 1, WALL, false),
                new Cell(0, 2, WALL, false)
            },
            new Cell[] {
                new Cell(1, 0, WALL, false),
                new Cell(1, 1, PASSAGE, true),
                new Cell(1, 2, WALL, false)
            },
            new Cell[] {
                new Cell(2, 0, WALL, false),
                new Cell(2, 1, WALL, false),
                new Cell(2, 2, WALL, false)
            }
        });

        BinaryTreeAlgorithm alg = new BinaryTreeAlgorithm();
        Maze actualMaze = alg.generate(height, width);

        assertArrayEquals(actualMaze.getGrid(), expectedMaze.getGrid());
    }

}
