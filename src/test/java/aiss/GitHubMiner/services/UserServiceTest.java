package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.User;
import aiss.GitHubMiner.models.User2;
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
        List<User> users = userService.getAllUsers(2, 2);
        assertFalse(users.isEmpty(), "The list of users is empty.");
        System.out.println("Tamaño total = "+ users.size());
        System.out.println(users);
    }

//    @Test
//    @DisplayName("Test de getUser")
//    void getUser() {
//        User user = userService.getUserName("mojombo");
//        assertNotNull(user.getId(), "The id can't be null.");
//        assertNotSame("mojombo", user.getLogin(), "Wrong username.");
//        assertNotSame("Tom Preston-Werner", user.getName(), "Wrong name.");
//        assertNotNull(user.getWeb_url(), "User URL can't be null.");
//        System.out.println(user);
//    }
}