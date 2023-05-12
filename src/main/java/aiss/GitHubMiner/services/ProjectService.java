package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.*;
import aiss.GitHubMiner.transformers.*;
import aiss.GitHubMiner.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
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

    public ProjectDef getProjectFromOwnerRepo(String owner, String repo, Integer sinceIssues, Integer sinceCommits, Integer maxPages)
            throws HttpClientErrorException{

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
        if (!ListIssues.isEmpty()) {
            for (Issue i : ListIssues) {
                List<Comment> listComments = new ArrayList<>();
                List<CommentDef> listCommentDef = new ArrayList<>();

                //obtenemos la informacion que nos falta(name) de author y assignee
                User iAuthor = i.getAuthor();
                User iAssignee = i.getAssignee();

                String iAuthorName = userService.getUserName(iAuthor.getLogin());
                String iAssigneeName = null;
                if (iAssignee != null) {
                    iAssigneeName = userService.getUserName(iAssignee.getLogin());
                }

                //en caso de que haya comments, extraemos el name de cada author
                if (i.comments != 0) {
                    listComments = commentService.getAllCommentsFromIssue(i.comments_url);

                    for (Comment c : listComments) {
                        User cAuthor = c.getAuthor();
                        String cAuthorName = userService.getUserName(cAuthor.getLogin());

                        //TRANSFORMACION
                        // transformamos "de abajo hacia arriba" primero los comments, luego los issue(dentro del bucle) y por ultimo el Project
                        // transformamos el author a UserDef y el comment a CommentDef
                        UserDef cAuthorDef = UserDef.transformaUser(cAuthor, cAuthorName);
                        CommentDef cDef = CommentDef.transformaComment(c, cAuthorDef);
                        listCommentDef.add(cDef);
                    }
                }

                //transformamos los user de Issue e issue a IssueDef
                UserDef iAuthorDef = UserDef.transformaUser(iAuthor, iAuthorName);
                UserDef iAssigneeDef = null;
                if (iAssignee != null) {
                    iAssigneeDef = UserDef.transformaUser(iAssignee, iAssigneeName);
                }

                IssueDef iDef = IssueDef.transformaIssue(i, listCommentDef, iAuthorDef, iAssigneeDef);
                listIssueDef.add(iDef);
            }
        }
        //transformamos los commits(en caso de que haya)
        if (!listCommits.isEmpty()) {
            listCommitDef = listCommits.stream().map(commit -> CommitDef.transformaCommit(commit)).toList();
        }
        ProjectDef pDef = ProjectDef.transformaProject(p, listCommitDef, listIssueDef);
        return pDef;
    }

    public ProjectDef postProjectFromOwnerRepo(String owner, String repo, Integer sinceIssues, Integer sinceCommits, Integer maxPages)
            throws RestClientException{

        String url = "http://localhost:8080/gitminer/projects";

        ProjectDef proyecto = getProjectFromOwnerRepo(owner, repo, sinceIssues, sinceCommits, maxPages);

        ResponseEntity<ProjectDef> httpResponse = restTemplate.postForEntity(url, proyecto, ProjectDef.class);
        return httpResponse.getBody();
    }
}
