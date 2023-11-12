package edu.hw3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Task8 {
    public static class BackwardIterator<T> implements Iterator<T> {
        private int cursor;
        private final T[] arr;

        public BackwardIterator(T[] arr) {
            this.arr = arr;
            cursor = arr.length - 1;
        }

        @Override
        public boolean hasNext() {
            return cursor > 0;
        }

        @Override
        public T next() {
            int i = cursor;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            cursor = i - 1;
            return arr[i];
        }
    }
}
