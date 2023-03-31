package aiss.GitHubMiner.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class commitServiceTest {
    @Autowired
    commitService commitService;
    @Test
    @DisplayName("Test de getAllCommits")
    void getAllCommits() {
        System.out.println(commitService.getAllCommits("spring-projects", "spring-framework", 5, 5));
    }
}