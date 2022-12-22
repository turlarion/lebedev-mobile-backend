package ru.turlarion.restdatabaseconnector.api.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyUserInfoPojo {

    private String login;

    private String password;

    private String name;

    private String phone;
}
