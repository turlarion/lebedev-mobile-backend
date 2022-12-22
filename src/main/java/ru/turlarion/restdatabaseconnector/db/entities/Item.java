package ru.turlarion.restdatabaseconnector.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Items")
public class Item {

    public Item(User user, String name, String description, String price, String picName, Date date) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picName = picName;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer item_id;


    private String name;

    private String description;

    private String price;

    private String picName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


//    @ManyToMany(mappedBy = "basket")
//    private Set<User> users;

}
