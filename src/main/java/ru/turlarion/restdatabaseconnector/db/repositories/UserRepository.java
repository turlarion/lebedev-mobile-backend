package ru.turlarion.restdatabaseconnector.db.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.turlarion.restdatabaseconnector.db.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByLogin(String login);

}
