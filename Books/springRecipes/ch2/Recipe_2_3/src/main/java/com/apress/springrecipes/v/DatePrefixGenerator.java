package com.apress.springrecipes.v;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
// 스프링에서는 @Primary를 붙여 후보빈을 명시. 여러 빈이 자동 연결 대상일 때 특정한 빈에 우선권을 부여.
@Primary
public class DatePrefixGenerator implements PrefixGenerator {

    @Override
    public String getPrefix() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(new Date());
    }
}
