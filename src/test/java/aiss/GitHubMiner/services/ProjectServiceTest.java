package aiss.GitHubMiner.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;
    @Test
    @DisplayName("Test de getProjectFromId")
    void getProjectFromId() {
        System.out.println(projectService.getProjectFromId(1148753));
    }

    @Test
    @DisplayName("Test de getProjectFromOwnerRepo")
    void getProjectFromOwnerRepo() {
        System.out.println(projectService.getProjectFromOwnerRepo("spring-projects", "spring-framework", 20,20,1));
    }
}