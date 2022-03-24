package com.apress.springrecipes.iv;

import org.springframework.beans.factory.annotation.Autowired;

public class SequenceGenerator {

    private PrefixGenerator prefixGenerator;
    private String suffix;
    private int initial;
    private int counter;

    public SequenceGenerator() {
    }

    public SequenceGenerator(PrefixGenerator prefixGenerator, String suffix, int initial) {
        this.prefixGenerator = prefixGenerator;
        this.suffix = suffix;
        this.initial = initial;
    }

    // @Autowired
    public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    // @Autowired는 메소드 인수의 이름과 개수에 상관없이 적용가능. 스프링은 각 메서드 인수형과 호환되는 빈을 찾아 연결함.
    @Autowired
    public void myOwnCustomInjectionName(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public synchronized String getSequence() {
        return prefixGenerator.getPrefix() +
                (initial + counter++) +
                suffix;
    }
}
