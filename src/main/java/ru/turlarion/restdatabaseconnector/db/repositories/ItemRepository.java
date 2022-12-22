package ru.turlarion.restdatabaseconnector.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.turlarion.restdatabaseconnector.db.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
