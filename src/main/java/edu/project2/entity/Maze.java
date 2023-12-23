package edu.project2.entity;

public class Maze {
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        this.grid = grid;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
