package edu.project4;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @AllArgsConstructor
public class Pixel {
    @Setter
    private Color color;
    @Setter
    private int hitCount;
    @Setter
    private double normal;
}
