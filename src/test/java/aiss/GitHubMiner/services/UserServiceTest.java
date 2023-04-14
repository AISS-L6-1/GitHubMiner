package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @DisplayName("Test de getAllUsers")
    void getAllUsers() {
        List<User> users = userService.getAllUsers(5, null);
        System.out.println(users.size());
        System.out.println(users);
    }

    @Test
    @DisplayName("Test de getUser")
    void getUser() {
        User user = userService.getUser("mojombo");
        System.out.println(user);
    }
}