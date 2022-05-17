package com.hyunsoo.swagger.dto;

import com.hyunsoo.swagger.model.Person;
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
}
