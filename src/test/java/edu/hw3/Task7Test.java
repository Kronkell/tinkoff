package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    public void shouldReturnTrueGivenMapWithNullAsKey() {
        TreeMap<String, String> treeMap = new TreeMap<>(new Task7.NullComparator());
        treeMap.put("KEKW", "LULW");
        treeMap.put(null, "test");

        assertThat(treeMap.containsKey(null)).isTrue();
    }
}
