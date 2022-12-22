package ru.turlarion.restdatabaseconnector.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    public User(String login, String password, String name, String phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private String name;

    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Item> items;

//    @ManyToMany
//    @JoinTable(
//        name = "basket",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "item_id")
//    )
//    private Set<Item> items;

}
