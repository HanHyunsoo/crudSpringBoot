package com.apress.springrecipes.ii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceService {

    // @Autowired로 sequenceDao 빈 (즉 SequenceDaoImpl 클래스)가 이 프로퍼티와 자동으로 연결됨
    // 요즘은 @Autowired를 안쓰고 클래스에 final 변수를 정의하고 생성자로 빈을 넣는 편임 (아래 주석 참고)
    /**
     *     private final SequenceDao sequenceDao;
     *
     *     public SequenceService(SequenceDao sequenceDao) {
     *         this.sequenceDao = sequenceDao;
     *     }
     */
    @Autowired
    private SequenceDao sequenceDao;

    public String generate(String sequenceId) {
        Sequence sequence = sequenceDao.getSequence(sequenceId);
        int value = sequenceDao.getNextValue(sequenceId);
        return sequence.getPrefix() + value + sequence.getSuffix();
    }
}
