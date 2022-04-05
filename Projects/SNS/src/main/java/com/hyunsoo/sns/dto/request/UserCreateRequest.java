package com.hyunsoo.sns.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {

    private String username;
    private String password1;
    private String password2;
}
