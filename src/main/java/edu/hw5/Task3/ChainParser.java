package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class ChainParser {
    private ChainParser next;

    public static ChainParser link(ChainParser first, ChainParser... chain) {
        ChainParser head = first;
        for (ChainParser nextInChain : chain) {
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
