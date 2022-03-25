package com.apress.springrecipes.ii.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.ii.DatePrefixGenerator;
import com.apress.springrecipes.ii.NumberPrefixGenerator;
import com.apress.springrecipes.ii.SequenceGenerator;


@Configuration
public class SequenceConfiguration {

    @Bean
    public DatePrefixGenerator datePrefixGenerator() {
        DatePrefixGenerator dpg = new DatePrefixGenerator();
        dpg.setPattern("yyyyMMdd");
        return dpg;
    }

    @Bean
    public NumberPrefixGenerator numberPrefixGenerator() {
        return new NumberPrefixGenerator();
    }

    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator sequence = new SequenceGenerator();
        sequence.setInitial(100000);
        sequence.setSuffix("A");
        return sequence;
    }
}
