package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.User;
import aiss.GitHubMiner.transformers.UserDef;
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
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    public List<UserDef> getAllUsers(Integer maxPages, Integer sinceDays)
            throws HttpClientErrorException {
        String url = "https://api.github.com/users";

        //como queremos que nuestros parametros(sinceDays y maxPages) sean opcionales, debemos comprobar cual de ellos no es nulo
        // y en funcion de si existe uno o ambos añadir la ? en la posicion correspondiente
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
        HttpEntity<User[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<User[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, User[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        String siguientePagina = funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;
        List<User> userList = new ArrayList<>();
        userList.addAll(Arrays.asList(httpResponse.getBody()));
        while (siguientePagina != null && page < maxPages) { //hay que comprobar que maxPages es diferente de null para poder evaluar <, funciona gracias a la evaluacion perezosa
            ResponseEntity<User[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, User[].class);
            userList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }

        return userList.stream().map(user -> getUser(user.getUsername())).toList();
    }

    public UserDef getUser(String username) {
        String url = "https://api.github.com/users/" + username; // <-- este username la API de GitHub lo llama "login"
        String token = Token.TOKEN;
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<UserDef> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<UserDef> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, UserDef.class);
        return httpResponse.getBody();
    }
}
