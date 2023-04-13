package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.User;
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
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    public List<User> getAllUsers(Integer maxPages, Integer sinceDays)
            throws HttpClientErrorException {
        String url = "https://api.github.com/users";

        //como queremos que nuestros parametros(sinceDays y maxPages) sean opcionales, debemos comprobar cual de ellos no es nulo
        // y en funcion de si existe uno o ambos añadir la ? en la posicion correspondiente
        if (sinceDays != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            url.concat("?since=" + since + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceDays != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
                url.concat("?since=" + since);
            }
            else {
                url.concat("?maxPages=" + maxPages);
            }
        }

        String token = "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";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<User[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<User[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, User[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        String siguientePagina = utils.funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 1;
        List<User> userList = new ArrayList<>();
        while (siguientePagina != null && (maxPages != null && page < maxPages)) { //hay que comprobar que maxPages es diferente de null para poder evaluar <, funciona gracias a la evaluacion perezosa
            ResponseEntity<User[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, User[].class);
            userList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }

        for(User u:userList) {
            User aux = getUser(u.getUsername());
            String name = aux.getName();
            u.setName(name);
        }

        System.out.println(userList.size());
        return userList;
    }

    public User getUser(String username) {
        String url = "https://api.github.com/users/"+username;
        String token = "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";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<User> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<User> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, User.class);
        return httpResponse.getBody();
    }
}
