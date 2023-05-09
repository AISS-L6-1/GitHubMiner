package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.*;
import aiss.GitHubMiner.transformers.*;
import aiss.GitHubMiner.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommitService commitService;
    @Autowired
    IssueService issueService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

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

    public ProjectDef getProjectFromOwnerRepo(String owner, String repo, Integer sinceIssues, Integer sinceCommits, Integer maxPages)
        throws HttpClientErrorException {
        String url = "https://api.github.com/repos" + "/" + owner + "/" + repo;
        String token = Token.TOKEN;
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project.class);

        //EXTRACCION
        //obtenemos el proyecto
        Project p = httpResponse.getBody();
        //obtenemos sus commits
        List<Commit> listCommits = commitService.getAllCommits(p.getUrl(), sinceCommits, maxPages);
        //obtenemos sus issues
        List<Issue> ListIssues = issueService.getAllIssues(p.getUrl(), sinceIssues, maxPages);

        List<IssueDef> listIssueDef = new ArrayList<>();
        List<CommitDef> listCommitDef = new ArrayList<>();

        //de cada issue obtenemos sus comments
        for(Issue i : ListIssues){
            List<Comment> listComments = new ArrayList<>();
            List<CommentDef> listCommentDef = new ArrayList<>();

            //obtenemos la informacion que nos falta(name) de author y assignee
            User iAuthor = i.getAuthor();
            User iAssignee = i.getAssignee();

            String iAuthorName = userService.getUserName(iAuthor.getLogin());
            String iAssigneeName = null;
            if(iAssignee != null) {
                iAssigneeName = userService.getUserName(iAssignee.getLogin());
            }

            //en caso de que haya comments, extraemos el name de cada author
            if (i.comments != 0){
                listComments = commentService.getAllCommentsFromIssue(i.comments_url);

                for(Comment c : listComments){
                     User cAuthor = c.getAuthor();
                     String cAuthorName = userService.getUserName(cAuthor.getLogin());

        //TRANSFORMACION
                // transformamos "de abajo hacia arriba" primero los comments, luego los issue(dentro del bucle) y por ultimo el Project
                // transformamos el author a UserDef y el comment a CommentDef
                     UserDef cAuthorDef =UserDef.transformaUser(cAuthor, cAuthorName);
                     CommentDef cDef = CommentDef.transformaComment(c, cAuthorDef);
                     listCommentDef.add(cDef);
                }
            }

            //transformamos los user de Issue e issue a IssueDef
            UserDef iAuthorDef = UserDef.transformaUser(iAuthor,iAuthorName);
            UserDef iAssigneeDef = null;
            if(iAssignee != null){
            iAssigneeDef = UserDef.transformaUser(iAssignee,iAssigneeName);}

            IssueDef iDef = IssueDef.transformaIssue(i,listCommentDef,iAuthorDef,iAssigneeDef);
            listIssueDef.add(iDef);
        }
        //transformamos los commits(en caso de que haya)
        if(listCommits.size() > 0){
            listCommitDef = listCommits.stream().map(commit -> CommitDef.transformaCommit(commit)).toList();
        }
        ProjectDef pDef = ProjectDef.transformaProject(p, listCommitDef,listIssueDef);
        return pDef;
    }

    public void postProjectFromOwnerRepo(String owner, String repo, Integer sinceIssues, Integer sinceCommits ,Integer maxPages){

        String url = "http://localhost:8080/gitminer/projects";

        HttpHeaders httpHeadersRequest = new HttpHeaders();
        HttpEntity<ProjectDef> httpRequest = new HttpEntity<>(null, httpHeadersRequest);

        ProjectDef proyecto = getProjectFromOwnerRepo(owner, repo, sinceIssues, sinceCommits, maxPages);

        ResponseEntity<ProjectDef> httpResponse = restTemplate.postForEntity(url, proyecto, ProjectDef.class);
    }
}
