package edu.project2.generate;

import edu.project2.entity.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
