package com.hyunsoo.swagger.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {

    @ApiModelProperty(notes = "이름", example = "한현수")
    private String name;

    @ApiModelProperty(notes = "나이", example = "24")
    private Short age;
}
