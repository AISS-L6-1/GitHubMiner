package aiss.GitHubMiner.services;

import aiss.GitHubMiner.transformers.ProjectDef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Test de getProjectFromOwnerRepo")
    void getProjectFromOwnerRepo() {
        ProjectDef projectDef = projectService.getProjectFromOwnerRepo("spring-projects", "spring-framework", 5,5,2);
        assertNotNull(projectDef, "Project is null.");
        assertNotNull(projectDef.getId(), "Project id can't be null.");
        assertEquals("spring-framework", projectDef.getName(), "Wrong repository name.");
        assertNotNull(projectDef.getWeb_url(), "Project url can't be null.");
        assertFalse(projectDef.getCommits().isEmpty(), "The list of commits can't be empty.");
        assertFalse(projectDef.getIssues().isEmpty(), "The list of issues can't be empty.");
        System.out.println(projectDef);

    }
}