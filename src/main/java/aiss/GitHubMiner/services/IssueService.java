package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Issue;
import aiss.GitHubMiner.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IssueService {
    @Autowired
    RestTemplate restTemplate;

    public List<Issue> getAllIssues(String owner, String repo, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {
        String url = "https://api.github.com/repos" + "/" + owner + "/" + repo + "/issues";

        if (sinceDays != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            url.concat("?created_after=" + since + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceDays != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
                url.concat("?created_after=" + since);
            }
            else if (maxPages != null){
                url.concat("?maxPages=" + maxPages);
            }
        }

        String token = "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";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Issue[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Issue[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Issue[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Issue> issueList = new ArrayList<>();
        issueList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = utils.funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;

        while (siguientePagina != null && (maxPages == null ? true:false || page < maxPages)) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Issue[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Issue[].class);
            issueList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return issueList;
    }
}
