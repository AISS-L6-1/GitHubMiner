package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Issue;
import aiss.GitHubMiner.transformers.IssueDef;
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
    @Autowired
    CommentService commentService;

    public List<IssueDef> getAllIssues(String owner, String repo, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {
        String url = "https://api.github.com/repos" + "/" + owner + "/" + repo + "/issues";

        if (sinceDays != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            url = url.concat("?created_after=" + since + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceDays != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
                url = url.concat("?created_after=" + since);
            }
            else if (maxPages != null){
                url = url.concat("?maxPages=" + maxPages);
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
        List<IssueDef> issueDefList = issueList.stream().map(i -> IssueDef.ofRaw(i,commentService, sinceDays, maxPages)).toList();
        return issueDefList;
    }

    public List<IssueDef> getAllIssues(String projectUrl, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {
        String issuesUrl = projectUrl + "/issues";

        if (sinceDays != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            issuesUrl = issuesUrl.concat("?created_after=" + since + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceDays != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
                issuesUrl = issuesUrl.concat("?created_after=" + since);
            }
            else if (maxPages != null){
                issuesUrl = issuesUrl.concat("?maxPages=" + maxPages);
            }
        }

        String token = "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";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Issue[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Issue[]> httpResponse = restTemplate.exchange(issuesUrl, HttpMethod.GET, httpRequest, Issue[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Issue> issueList = new ArrayList<>();
        issueList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = utils.funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;

        while (siguientePagina != null && (maxPages == null ? true:false || page < maxPages)) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Issue[]> responseEntity = restTemplate.exchange(issuesUrl + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Issue[].class);
            issueList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        List<IssueDef> issueDefList = issueList.stream().map(i -> IssueDef.ofRaw(i,commentService, sinceDays, maxPages)).toList();
        return issueDefList;
    }

}
