package edu.hw3;

import java.util.Iterator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    public void shouldIterateBackwardsGivenArrOfInts() {
        Iterator<Integer> iterator = new Task8.BackwardIterator<>(new Integer[]{1, 2, 3});

        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    public void shouldIterateBackwardsGivenArrOfStrings() {
        Iterator<String> iterator = new Task8.BackwardIterator<>(new String[]{"1", "2", "3"});

        assertThat(iterator.next()).isEqualTo("3");
        assertThat(iterator.next()).isEqualTo("2");
        assertThat(iterator.next()).isEqualTo("1");
    }
}
