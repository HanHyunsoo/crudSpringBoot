package com.apress.springrecipes.vii.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.apress.springrecipes.vii.PrefixGenerator;
import com.apress.springrecipes.vii.SequenceGenerator;


@Configuration
// @Import(PrefixConfiguration.class)를 붙이면 PrefixConfiguration 클래스에 정의한 POJO를
// 모두 현재 구성 클래스의 스코프로 가져올 수 있다.
@Import(PrefixConfiguration.class)
public class SequenceConfiguration {

    // @Value와 SpEL을 써서 PrefixConfiguration 클래스에 선언된 datePrefixGenerator 빈을
    // prefixGenerator 필드에 주입한다.
    @Value("#{datePrefixGenerator}")
    private PrefixGenerator prefixGenerator;

    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator sequence = new SequenceGenerator();
        sequence.setInitial(100000);
        sequence.setSuffix("A");
        sequence.setPrefixGenerator(prefixGenerator);
        return sequence;
    }
}
