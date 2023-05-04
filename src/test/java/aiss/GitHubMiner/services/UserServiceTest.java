package aiss.GitHubMiner.services;

import aiss.GitHubMiner.transformers.UserDef;
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
        List<UserDef> users = userService.getAllUsers(2, 2);
        assertFalse(users.isEmpty(), "The list of users is empty.");
        System.out.println("Tamaño total = "+ users.size());
        System.out.println(users);
    }

    @Test
    @DisplayName("Test de getUser")
    void getUser() {
        UserDef user = userService.getUser("mojombo");
        assertNotNull(user.getId(), "The id can't be null.");
        assertNotSame("mojombo", user.getUsername(), "Wrong username.");
        assertNotSame("Tom Preston-Werner", user.getName(), "Wrong name.");
        assertNotNull(user.getUrl(), "User URL can't be null.");
        System.out.println(user);
    }
}