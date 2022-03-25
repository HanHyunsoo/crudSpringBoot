package com.apress.springrecipes.ii;

import javax.inject.Inject;

public class SequenceGenerator {

    // @Autowired, @Resource의 경우처럼 타입이 같은 POJO가 여러개 있을 때
    // @Inject을 이용해 이름으로 자동 연결을 하려면 먼저 POJO 주입 클래스와 주입 지점을 구별하기 위해
    // 커스텀 애너테이션(사용자가 정의한)을 작성해야함
    @Inject
    @DatePrefixAnnotation
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

    public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
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
