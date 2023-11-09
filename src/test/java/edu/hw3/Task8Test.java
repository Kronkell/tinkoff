package edu.hw3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {
    @Test
    public void shouldIterateBackwardsGivenArrOfInts() {
        Iterator<Integer> iterator = new Task8.BackwardIterator<>(new Integer[] {1, 2, 3});

        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    public void shouldIterateBackwardsGivenArrOfStrings() {
        Iterator<String> iterator = new Task8.BackwardIterator<>(new String[] {"1", "2", "3"});

        assertThat(iterator.next()).isEqualTo("3");
        assertThat(iterator.next()).isEqualTo("2");
        assertThat(iterator.next()).isEqualTo("1");
    }

    @Test
    public void shouldIterateBackwardsGivenArrOfSizeOne() {
        Iterator<Integer> iterator = new Task8.BackwardIterator<>(new Integer[] {1});

        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    public void shouldThrowExceptionGivenEmptyArray() {
        Iterator<Integer> iterator = new Task8.BackwardIterator<>(new Integer[] {});

        assertThrows(NoSuchElementException.class, iterator::next);
    }
    @Test
    public void shouldCalculateIfHasNext() {
        Iterator<Integer> iterator = new Task8.BackwardIterator<>(new Integer[] {1, 2, 3});

        assertThat(iterator.hasNext()).isEqualTo(true);
        iterator.next();
        assertThat(iterator.hasNext()).isEqualTo(true);
        iterator.next();
        assertThat(iterator.hasNext()).isEqualTo(false);
    }

    @Test
    public void shouldCalculateIfHasNextGivenEmptyArray() {
        Iterator<Integer> iterator = new Task8.BackwardIterator<>(new Integer[] {});

        assertThat(iterator.hasNext()).isEqualTo(false);
    }
}
