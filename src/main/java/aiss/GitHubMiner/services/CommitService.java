package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Commit;
import aiss.GitHubMiner.transformers.CommitDef;
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
public class CommitService {
    @Autowired
    RestTemplate restTemplate;

    public List<Commit> getAllCommits(String owner, String repo, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {
        String url = "https://api.github.com/repos" + "/" + owner + "/" + repo + "/commits";

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
        HttpEntity<Commit> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        List<Commit> commitList = new ArrayList<>();
        ResponseEntity<Commit[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Commit[].class);
        commitList.addAll(Arrays.asList(httpResponse.getBody()));
        String siguientePagina = funciones.getNextPageUrl(httpResponse.getHeaders());
        Integer page = 2;
        while (siguientePagina != null && page < maxPages) {
            ResponseEntity<Commit[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Commit[].class);
            commitList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return commitList;
    }


    public List<Commit> getAllCommits(String projectUrl, Integer sinceCommits, Integer maxPages)
            throws HttpClientErrorException {

        String commitsUrl = projectUrl +"/commits";

        if (sinceCommits != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceCommits);
            commitsUrl = commitsUrl.concat("?since=" + since);
        }
        if (maxPages == null) {
            maxPages = Integer.MAX_VALUE;
        }


        String token = Token.TOKEN;
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Commit> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Commit[]> httpResponse = restTemplate.exchange(commitsUrl, HttpMethod.GET, httpRequest, Commit[].class);
        String siguientePagina = funciones.getNextPageUrl(httpResponse.getHeaders());
        Integer page = 2;
        List<Commit> commitList = new ArrayList<>();
        commitList.addAll(Arrays.asList(httpResponse.getBody()));
        while (siguientePagina != null && (maxPages != null && page < maxPages)) {
            ResponseEntity<Commit[]> responseEntity = restTemplate.exchange(commitsUrl + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Commit[].class);
            commitList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return commitList;
    }
}
