package com.apress.springrecipes.i.sequence.config;

import com.apress.springrecipes.i.sequence.SequenceGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration은 이 클래스가 구성 클래스임을 스프링에 알립니다.
스프링은 @Configuration이 달린 클래스를 보면 그 안에서 빈 인스턴스 정의부, 즉 @Bean을 붙인 자바 메소드를 찾습니다.
 */
@Configuration
public class SequenceGeneratorConfiguration {

    /*
     메소드에 @Bean을 붙이면 그 메소드와 동일한 이름의 빈이 생성됨
     (따로 명시하려면 매개 변수값으로 name={원하는 이름}으로 지정)
     */
    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator seqgen = new SequenceGenerator();
        seqgen.setPrefix("30");
        seqgen.setSuffix("A");
        seqgen.setInitial(100000);
        return seqgen;
    }
}
