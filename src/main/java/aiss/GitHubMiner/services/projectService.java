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
public class projectService {
    @Autowired
    RestTemplate restTemplate;

    public Project getProjectFromId(Integer id) {
        String url = "https://api.github.com/projects" + "/" + id.toString();
        String token = "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";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project.class);
        return httpResponse.getBody();
    }
}
