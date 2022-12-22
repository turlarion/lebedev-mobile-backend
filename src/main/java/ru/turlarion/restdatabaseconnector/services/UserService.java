package ru.turlarion.restdatabaseconnector.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.turlarion.restdatabaseconnector.api.pojos.ModifyUserInfoPojo;
import ru.turlarion.restdatabaseconnector.db.entities.User;
import ru.turlarion.restdatabaseconnector.db.repositories.UserRepository;
import ru.turlarion.restdatabaseconnector.api.pojos.NewUserPojo;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(NewUserPojo newUser) {
        User newUserDTO = new User(newUser.getLogin(), newUser.getPassword(),
            newUser.getName(), newUser.getPhone());
        userRepository.save(newUserDTO);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).isEmpty() ? null : userRepository.findById(id).get();
    }

    public User getUser(String login) {
        return userRepository.findByLogin(login).isPresent() ? userRepository.findByLogin(login).get() : null;
    }

    public boolean updateUserInfo(Integer id, ModifyUserInfoPojo modifyUserInfoPojo) {

        if (modifyUserInfoPojo.getLogin() == null && modifyUserInfoPojo.getPassword() == null
            && modifyUserInfoPojo.getName() == null && modifyUserInfoPojo.getPhone() == null) {
            return false;
        }

        if (
            (modifyUserInfoPojo.getLogin() != null && modifyUserInfoPojo.getLogin().isBlank())
            || (modifyUserInfoPojo.getPassword() != null && modifyUserInfoPojo.getPassword().isBlank())
            || (modifyUserInfoPojo.getName() != null && modifyUserInfoPojo.getName().isBlank())
            || (modifyUserInfoPojo.getPhone() != null && modifyUserInfoPojo.getPhone().isBlank())
        ) {
            return false;
        }

        User user = userRepository.getReferenceById(id);
        if (modifyUserInfoPojo.getLogin() != null) {
            user.setLogin(modifyUserInfoPojo.getLogin());
        }
        if (modifyUserInfoPojo.getPassword() != null) {
            user.setPassword(modifyUserInfoPojo.getPassword());
        }
        if (modifyUserInfoPojo.getName() != null) {
            user.setName(modifyUserInfoPojo.getName());
        }
        if (modifyUserInfoPojo.getPhone() != null) {
            user.setPhone(modifyUserInfoPojo.getPhone());
        }

        userRepository.save(user);

        return true;
    }
}
