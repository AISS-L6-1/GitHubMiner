package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {
    @Autowired
    IssueService issueService;
    @Test
    @DisplayName("Test de getAllIssues")
    void getAllIssues() {
        List<Issue> issueList = issueService.getAllIssues("spring-projects", "spring-framework",null,2);
        System.out.println(issueList);
    }
}