package ru.turlarion.restdatabaseconnector.api.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewItemPojo {

    private Integer userId;

    private String name;

    private String description;

    private String price;

    private String picName;

}
