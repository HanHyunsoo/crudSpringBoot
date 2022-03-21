package com.apress.springrecipes.ii.sequence;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/*
@Component를 붙이면 스프링은 이 클래스를 이용해 POJO를 생성.
이 어노테이션 안에 넣은 값(sequenceDao)은 빈 인스턴스 ID로 설정되며
지정을 안해줄 경우 소문자로 시작하는 비 규격 클래스명을 빈 이름으로 기본 할당
ex) 아래 클래스에 변수 값이 없으면 sequenceDaoImpl이라는 이름으로 빈이 생성됨
 */
@Component("sequenceDao")
public class SequenceDaoImpl implements SequenceDao {

    private final Map<String, Sequence> sequences = new HashMap<>();
    private final Map<String, AtomicInteger> values = new HashMap<>();

    public SequenceDaoImpl() {
        sequences.put("IT", new Sequence("IT", "30", "A"));
        values.put("IT", new AtomicInteger(10000));
    }

    @Override
    public Sequence getSequence(String sequenceId) {
        return sequences.get(sequenceId);
    }

    @Override
    public int getNextValue(String sequenceId) {
        AtomicInteger value = values.get(sequenceId);
        return value.getAndIncrement();
    }
}
