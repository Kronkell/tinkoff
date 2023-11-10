package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Middleware {
    private Middleware next;

    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }

        return first;
    }

    public abstract Optional<LocalDate> check(String date);

    protected Optional<LocalDate> checkNext(String date) {
        if (next == null) {
            return Optional.empty();
        }

        return next.check(date);
    }
}
