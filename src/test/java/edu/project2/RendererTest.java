package edu.project2;

import java.util.List;
import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.print.PrettyPrintRenderer;
import org.junit.jupiter.api.Test;
import static edu.project2.entity.Cell.Type.PASSAGE;
import static edu.project2.entity.Cell.Type.WALL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RendererTest {
    @Test
    public void renderGridGivenGeneratedMaze() {
        Maze maze = new Maze(3, 3, new Cell[][] {
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
        String expectedGrid =
            "██████\n" +
                "██  ██\n" +
                "██████\n";

        PrettyPrintRenderer renderer = new PrettyPrintRenderer();
        String actualGrid = renderer.render(maze);

        assertThat(actualGrid).isEqualTo(expectedGrid);
    }

    @Test
    public void renderGridGivenGeneratedMazeWithPath() {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        Maze maze = new Maze(3, 3, new Cell[][] {
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
        String expectedGrid =
            "██████\n" +
                "██" + ANSI_GREEN + "██" + ANSI_RESET + "██\n" +
                "██████\n";

        PrettyPrintRenderer renderer = new PrettyPrintRenderer();
        String actualGrid = renderer.renderWithPath(
            maze,
            List.of(new Coordinate(1, 1)),
            new Coordinate(1, 1)
        );

        assertThat(actualGrid).isEqualTo(expectedGrid);
    }
}
