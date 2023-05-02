package aiss.GitHubMiner.services;

import aiss.GitHubMiner.transformers.UserDef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @DisplayName("Test de getAllUsers")
    void getAllUsers() {
        List<UserDef> users = userService.getAllUsers(5, 2);
        System.out.println(users.size());
        System.out.println(users);
    }

    @Test
    @DisplayName("Test de getUser")
    void getUser() {
        UserDef user = userService.getUser("mojombo");
        System.out.println(user);
    }
}