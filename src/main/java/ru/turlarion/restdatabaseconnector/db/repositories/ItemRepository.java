package ru.turlarion.restdatabaseconnector.db.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.turlarion.restdatabaseconnector.db.entities.Item;
import ru.turlarion.restdatabaseconnector.db.entities.User;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByUser(User user);
}
