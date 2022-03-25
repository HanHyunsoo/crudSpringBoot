package com.apress.springrecipes.i;

import javax.annotation.Resource;


public class SequenceGenerator {

    // @Qualifier와 기능은 동일하지만
    // @Resource는 JSR-250에 규정된 에너테이션, @Autowired, @Qualifier는 스프링 프레임워크에서만 사용 가능한 에너테이션
    // 필드와 세터 주입만 가능하고 생성자와 다중 인자 메소드 주입은 지원 x(@Autowired, @Qualifier는 가능)
    @Resource(name = "datePrefixGenerator")
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
