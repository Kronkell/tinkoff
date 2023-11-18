package edu.hw6;

import edu.hw6.Task1.DiskMap;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    static DiskMap diskMap;

    static {
        try {
            diskMap = new DiskMap();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void putTest() {
        diskMap.put("KEKW", "LULW");

        assertThat(diskMap.containsKey("KEKW")).isTrue();
    }

    @Test
    public void getTest() {
        diskMap.put("KEKW", "LULW");
        diskMap.put("CARL", "CARLA");

        assertThat(diskMap.get("CARL")).isEqualTo("CARLA");
    }

    @Test
    public void removeTest() {
        diskMap.put("KEKW", "LULW");
        diskMap.put("CARL", "CARLA");

        assertThat(diskMap.remove("CARL")).isEqualTo("CARLA");
        assertThat(diskMap.containsKey("CARL")).isFalse();
    }

    @Test
    public void clearTest() {
        diskMap.put("KEKW", "LULW");
        diskMap.put("CARL", "CARLA");
        diskMap.clear();

        assertThat(diskMap.isEmpty()).isTrue();
    }
}
