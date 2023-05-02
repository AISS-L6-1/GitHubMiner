package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Issue;
import aiss.GitHubMiner.transformers.IssueDef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IssueServiceTest {
    @Autowired
    IssueService issueService;
    @Test
    @DisplayName("Test de getAllIssues")
    void getAllIssues() {
        List<IssueDef> issueList = issueService.getAllIssues("spring-projects", "spring-framework",null,2);
        System.out.println(issueList);
    }
}