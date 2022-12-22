package ru.turlarion.restdatabaseconnector.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {

    private Integer user_id;

    private String login;

    private String password;

    private String name;

    private String phone;

}
