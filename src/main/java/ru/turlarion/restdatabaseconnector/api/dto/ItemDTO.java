package ru.turlarion.restdatabaseconnector.api.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemDTO {

    private Integer item_id;

    private Integer user_id;

    private String name;

    private String description;

    private String price;

    private String picName;

    private Date date;

}
