package ru.turlarion.restdatabaseconnector.api.rest;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.turlarion.restdatabaseconnector.api.dto.UserDTO;
import ru.turlarion.restdatabaseconnector.api.pojos.ModifyUserInfoPojo;
import ru.turlarion.restdatabaseconnector.db.entities.User;
import ru.turlarion.restdatabaseconnector.api.pojos.AuthUserPojo;
import ru.turlarion.restdatabaseconnector.api.pojos.NewUserPojo;
import ru.turlarion.restdatabaseconnector.services.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public void registerNewUser(@RequestBody NewUserPojo newUser) {
        userService.addNewUser(newUser);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
        return userService.getUsers()
            .stream()
            .map(user ->
                UserDTO.builder()
                    .user_id(user.getUser_id())
                    .login(user.getLogin())
                    .password(user.getPassword())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .build())
            .toList();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public UserDTO getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return user == null ? null :
            UserDTO.builder()
                .user_id(user.getUser_id())
                .login(user.getLogin())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/auth")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthUserPojo authPojo) {
        User user = userService.getUser(authPojo.getLogin());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (user.getPassword().equals(authPojo.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is invalid");
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/modify/{id}")
    public ResponseEntity<String> modifyUser(@PathVariable Integer id, @RequestBody ModifyUserInfoPojo modifyUserInfoPojo) {
        if (userService.updateUserInfo(id, modifyUserInfoPojo)) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check request body!");
    }

}
