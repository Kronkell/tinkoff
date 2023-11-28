package edu.project2.entity;

public record Cell(int row, int column, Type type, boolean visited) {
    public enum Type { WALL, PASSAGE }
}
