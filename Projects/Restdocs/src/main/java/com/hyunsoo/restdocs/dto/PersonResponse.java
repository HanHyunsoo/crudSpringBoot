package com.hyunsoo.restdocs.dto;

import com.hyunsoo.restdocs.model.Person;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PersonResponse {

    private final Long id;
    private final String name;
    private final Short age;
    private final LocalDateTime created;

    public PersonResponse(Person entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.created = entity.getCreated();
    }

    @Builder
    public PersonResponse(Long id, String name, Short age, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.created = created;
    }
}
