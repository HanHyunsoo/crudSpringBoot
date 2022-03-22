package com.apress.springrecipes.i.config;

import com.apress.springrecipes.i.DatePrefixGenerator;
import com.apress.springrecipes.i.SequenceGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceConfiguration {

    @Bean
    public DatePrefixGenerator datePrefixGenerator() {
        DatePrefixGenerator dpg = new DatePrefixGenerator();
        dpg.setPattern("yyyyMMdd");
        return dpg;
    }

    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator sequence = new SequenceGenerator();
        sequence.setInitial(100000);
        sequence.setSuffix("A");
        // datePrefixGenerator()는 빈으로 등록되어 있기 때문에 따로 안만들어도 접근 가능
        sequence.setPrefixGenerator(datePrefixGenerator());
        return sequence;
    }
}
