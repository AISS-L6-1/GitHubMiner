package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IssueService {
    @Autowired
    RestTemplate restTemplate;


    public Project getAllIssues(Integer projectId) {
        String url = "https://api.github.com/projects" + "/" + projectId.toString();
        String token = "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";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project.class);
        return httpResponse.getBody();
    }
}
