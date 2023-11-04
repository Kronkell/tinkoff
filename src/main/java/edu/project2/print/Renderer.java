package edu.project2.print;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String renderWithPath(Maze maze, List<Coordinate> path, Coordinate start);
}
