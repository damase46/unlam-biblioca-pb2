package com.unlam.library.utils;

public class Sequence {

    private Long count;

    public Sequence() {
        count = 0L;
    }

    public Long getSequence() {
        return count++;
    }
}
