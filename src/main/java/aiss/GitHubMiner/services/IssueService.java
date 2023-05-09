package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Comment;
import aiss.GitHubMiner.models.Issue;
import aiss.GitHubMiner.transformers.IssueDef;
import aiss.GitHubMiner.utils.Token;
import aiss.GitHubMiner.utils.funciones;
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
    @Autowired
    UserService userService;

    public List<Issue> getAllIssues(String owner, String repo, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {
        String url = "https://api.github.com/repos" + "/" + owner + "/" + repo + "/issues";

        if (sinceDays != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            url = url.concat("?since=" + since);
        }
        if (maxPages == null) {
            maxPages = Integer.MAX_VALUE;
        }

        String token = Token.TOKEN;
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Issue[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Issue[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Issue[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Issue> issueList = new ArrayList<>();
        issueList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;

        while (siguientePagina != null && page < maxPages) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Issue[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Issue[].class);
            issueList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return issueList;
    }

    public List<Issue> getAllIssues(String projectUrl, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {


        String issuesUrl = projectUrl + "/issues";

        if (sinceDays != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            issuesUrl = issuesUrl.concat("?since=" + since);
        }
        if (maxPages == null) {
            maxPages = Integer.MAX_VALUE;
        }

        String token = Token.TOKEN;
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Issue[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Issue[]> httpResponse = restTemplate.exchange(issuesUrl, HttpMethod.GET, httpRequest, Issue[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Issue> issueList = new ArrayList<>();
        issueList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;

        while (siguientePagina != null && page < maxPages) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Issue[]> responseEntity = restTemplate.exchange(issuesUrl + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Issue[].class);
            issueList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return issueList;
    }

}
