package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Commit;
import aiss.GitHubMiner.transformers.CommitDef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CommitServiceTest {
    @Autowired
    CommitService commitService;
    @Test
    @DisplayName("Test de getAllCommits")
    void getAllCommits() {
        List<Commit> commitDefList = commitService.getAllCommits("spring-projects", "spring-framework", 4, 2);
        assertFalse(commitDefList.isEmpty(), "The list of commits is empty.");
        System.out.println(commitDefList);
    }
}