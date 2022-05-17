package com.hyunsoo.swagger.dto;

import com.hyunsoo.swagger.model.Person;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PersonResponse {

    @ApiModelProperty(notes = "기본키", example = "1")
    private final Long id;

    @ApiModelProperty(notes = "이름", example = "한현수")
    private final String name;

    @ApiModelProperty(notes = "나이", example = "24")
    private final Short age;

    @ApiModelProperty(notes = "생성시간", example = "2022-05-17 14:21:18.187849")
    private final LocalDateTime created;

    public PersonResponse(Person entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.created = entity.getCreated();
    }
}
