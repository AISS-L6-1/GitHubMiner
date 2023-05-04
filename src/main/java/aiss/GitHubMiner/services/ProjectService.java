package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Project;
import aiss.GitHubMiner.transformers.ProjectDef;
import aiss.GitHubMiner.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommitService commitService;
    @Autowired
    IssueService issueService;

//    public List<ProjectDef> getAllProjects(Integer sinceDays, Integer sinceIssues, Integer sinceCommits, Integer maxPages)
//            throws HttpClientErrorException {
//        String url = "https://api.github.com/projects";
//
//        //como queremos que nuestros parametros(sinceDays y maxPages) sean opcionales, debemos comprobar cual de ellos no es nulo
//        // y en funcion de si existe uno o ambos añadir la ? en la posicion correspondiente
//        if (sinceDays != null) {
//            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
//            url = url.concat("?since=" + since);
//        }
//        if (maxPages == null) {
//            maxPages = Integer.MAX_VALUE;
//        }
//
//        String token = Token.TOKEN;
//        HttpHeaders httpHeadersRequest = new HttpHeaders();
//        httpHeadersRequest.setBearerAuth(token);
//        HttpEntity<Project[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
//        ResponseEntity<Project[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project[].class);
//        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();
//
//        List<Project> projectList = new ArrayList<>();
//        projectList.addAll(Arrays.asList(httpResponse.getBody()));
//
//        String siguientePagina = funciones.getNextPageUrl(httpResponseHeaders);
//        Integer page = 2;
//
//        while (siguientePagina != null && page < maxPages) { //compruebo que maxPages sea distinto de null para poder avanzar
//            ResponseEntity<Project[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Project[].class);
//            projectList.addAll(Arrays.asList(responseEntity.getBody()));
//            siguientePagina = funciones.getNextPageUrl(responseEntity.getHeaders());
//            page++;
//        }
//        Integer finalMaxPages = maxPages;
//        List<ProjectDef> projectDefList = projectList.stream().map(p -> ProjectDef.ofRaw(p,commitService,issueService,sinceIssues, sinceCommits, finalMaxPages)).toList();
//        return projectDefList;
//    }

    /*
    public Project getProjectFromId(Integer id) {
        String url = "https://api.github.com/projects/" + id.toString();
        String token = Token.TOKEN;
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project.class);
        return httpResponse.getBody();
    }
    */


    public ProjectDef getProjectFromOwnerRepo(String owner, String repo, Integer sinceIssues, Integer sinceCommits, Integer maxPages) {
        String url = "https://api.github.com/repos" + "/" + owner + "/" + repo;
        String token = Token.TOKEN;
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project.class);

        Project p = httpResponse.getBody();
        return ProjectDef.ofRaw(p,commitService,issueService, sinceIssues, sinceCommits, maxPages);
    }
}
