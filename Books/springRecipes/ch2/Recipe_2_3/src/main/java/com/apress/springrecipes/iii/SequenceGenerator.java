package com.apress.springrecipes.iii;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGenerator {

    // 배열형 프로퍼티에 @Autowired를 붙이면 스프링은 배치된 빈을 모두 찾아 자동 연결
    // PrefixGenerator와 연관된 클래스들이 배열에 들어감(SequenceConfiguration에 있는 datePrefixGenerator, numberPrefixGenerator)
    @Autowired
    private PrefixGenerator[] prefixGenerators;
    private String suffix;
    private int initial;
    private final AtomicInteger counter = new AtomicInteger();

    public SequenceGenerator() {
    }

    public SequenceGenerator(PrefixGenerator[] prefixGenerators, String suffix, int initial) {
        this.prefixGenerators = prefixGenerators;
        this.suffix = suffix;
        this.initial = initial;
    }

    public void setPrefixGenerators(PrefixGenerator[] prefixGenerators) {
        this.prefixGenerators = prefixGenerators;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public String getSequence() {
        StringBuilder builder = new StringBuilder();
        for (PrefixGenerator prefix : prefixGenerators) {
            builder.append(prefix.getPrefix());
            builder.append("-");
        }
        builder.append(initial).append(counter.getAndIncrement()).append(suffix);
        return builder.toString();
    }
}
