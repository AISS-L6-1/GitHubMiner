package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Comment;
import aiss.GitHubMiner.utils.funciones;
import aiss.GitHubMiner.utils.Token;
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
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    //devuelve todos los comentarios de un repositorio(no tendrá funcionalidad real)
    public List<Comment> getAllComments(String owner, String repo, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {
        String url = "https://api.github.com/repos" + "/" + owner + "/" + repo + "/issues/comments";

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
        HttpEntity<Comment[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Comment[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Comment[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Comment> commentList = new ArrayList<>();
        commentList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;

        while (siguientePagina != null &&  page < maxPages) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Comment[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Comment[].class);
            commentList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return commentList;
    }

    @Autowired
    UserService userService;
    //devuelve todos los comentarios de un Issue dado, el parametro es una propiedad de issue que nos facilita el acceso a los comments
    public List<Comment> getAllCommentsFromIssue(String commentsUrl)
            throws HttpClientErrorException {

        String token = Token.TOKEN;
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Comment[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Comment[]> httpResponse = restTemplate.exchange(commentsUrl, HttpMethod.GET, httpRequest, Comment[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Comment> commentList = new ArrayList<>();
        commentList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;

        while (siguientePagina != null) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Comment[]> responseEntity = restTemplate.exchange(commentsUrl + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Comment[].class);
            commentList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return commentList;
    }
}
