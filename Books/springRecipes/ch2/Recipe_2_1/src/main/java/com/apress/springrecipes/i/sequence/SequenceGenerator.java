package com.apress.springrecipes.i.sequence;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGenerator {

    private String prefix;
    private String suffix;
    private int initial;
    private final AtomicInteger counter = new AtomicInteger();

    public SequenceGenerator() {
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public String getSequence() {

        return prefix +
                initial +
                counter.getAndIncrement() +
                suffix;
    }
}
