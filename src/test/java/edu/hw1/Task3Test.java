package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    public void shouldReturnTrueGivenFirstArrIsInside() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {0, 6};

        boolean actualResult = Task3.isNestable(arr1, arr2);

        assertThat(actualResult).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseGivenFirstArrIsOutside() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {2, 3};

        boolean actualResult = Task3.isNestable(arr1, arr2);

        assertThat(actualResult).isEqualTo(false);
    }

    @Test
    public void shouldReturnFalseGivenEmptyArrays() {
        int[] arr1 = {};
        int[] arr2 = {};

        boolean actualResult = Task3.isNestable(arr1, arr2);

        assertThat(actualResult).isEqualTo(false);
    }

    @Test
    public void shouldReturnFalseGivenIdenticalArrays() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {1, 2, 3, 4};

        boolean actualResult = Task3.isNestable(arr1, arr2);

        assertThat(actualResult).isEqualTo(false);
    }
}
