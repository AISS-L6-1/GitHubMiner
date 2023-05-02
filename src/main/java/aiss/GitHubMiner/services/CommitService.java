package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Comment;
import aiss.GitHubMiner.models.Commit;
import aiss.GitHubMiner.models.CommitDef;
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

    public List<CommitDef> getAllCommits(String owner, String repo, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {
        String url = "https://api.github.com/repos" + "/" + owner + "/" + repo + "/commits";

        if (sinceDays != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            url = url.concat("?since=" + since + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceDays != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
                url = url.concat("?since=" + since);
            } else {
                url = url.concat("?maxPages=" + maxPages);
            }
        }

        String token = "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";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Commit> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Commit[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Commit[].class);
        String siguientePagina = utils.funciones.getNextPageUrl(httpResponse.getHeaders());
        Integer page = 1;
        List<Commit> commitList = new ArrayList<>();
        while (siguientePagina != null && (maxPages != null && page < maxPages)) {
            ResponseEntity<Commit[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Commit[].class);
            commitList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return commitList.stream().map(commit -> utils.adaptadores.transforma(commit)).toList();
    }


    public List<CommitDef> getAllCommits(String projectUrl, Integer sinceCommits, Integer maxPages)
            throws HttpClientErrorException {

        String commitsUrl = projectUrl +"/commits";

        if (sinceCommits != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceCommits);
            commitsUrl = commitsUrl.concat("?since=" + since + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceCommits != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceCommits);
                commitsUrl = commitsUrl.concat("?since=" + since);
            } else {
                commitsUrl = commitsUrl.concat("?maxPages=" + maxPages);
            }
        }


        String token = "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";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Commit> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Commit[]> httpResponse = restTemplate.exchange(commitsUrl, HttpMethod.GET, httpRequest, Commit[].class);
        String siguientePagina = utils.funciones.getNextPageUrl(httpResponse.getHeaders());
        Integer page = 1;
        List<Commit> commitList = new ArrayList<>();
        while (siguientePagina != null && (maxPages != null && page < maxPages)) {
            ResponseEntity<Commit[]> responseEntity = restTemplate.exchange(commitsUrl + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Commit[].class);
            commitList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return commitList.stream().map(commit -> utils.adaptadores.transforma(commit)).toList();
    }
}
