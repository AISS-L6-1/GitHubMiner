package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.exceptions.AuthorizationException;
import aiss.GitHubMiner.exceptions.GitMinerNotRunningException;
import aiss.GitHubMiner.exceptions.ProjectNotFoundException;
import aiss.GitHubMiner.services.ProjectService;
import aiss.GitHubMiner.transformers.ProjectDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import javax.naming.AuthenticationException;
import java.net.ConnectException;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    //GET http://localhost:8080/api/projects/{owner}/{repo}
    @GetMapping("/{owner}/{repoName}")
    public ProjectDef findOne(@PathVariable String owner, @PathVariable String repoName, @RequestParam(value = "sinceIssues", defaultValue = "5") Integer sinceIssues, @RequestParam(value = "sinceCommits", defaultValue = "5") Integer sinceCommits, @RequestParam(value = "maxPages", defaultValue = "2") Integer maxPages)
            throws ProjectNotFoundException, AuthorizationException{
        try {
            ProjectDef project = projectService.getProjectFromOwnerRepo(owner, repoName, sinceIssues, sinceCommits, maxPages);
            return project;
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProjectNotFoundException("Che no lo encontré");
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new AuthorizationException("revisa que token esté bien creado que te lo he dicho 20 veces");

        }
    }


    //POST http://localhost:8082/api/projects/{owner}/{repo}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{owner}/{repoName}")
    public ProjectDef create(@PathVariable String owner, @PathVariable String repoName, @RequestParam(value = "sinceIssues", defaultValue = "5") Integer sinceIssues, @RequestParam(value = "sinceCommits", defaultValue = "5") Integer sinceCommits, @RequestParam(value = "maxPages", defaultValue = "2") Integer maxPages)
            throws GitMinerNotRunningException, ProjectNotFoundException , AuthorizationException{
        try {
            ProjectDef projectDef = projectService.postProjectFromOwnerRepo(owner, repoName, sinceIssues, sinceCommits, maxPages);
            return projectDef;

        } catch (HttpClientErrorException.NotFound e) {
            throw new ProjectNotFoundException("Che no lo encontré");

        } catch (HttpClientErrorException.Unauthorized e) {
            throw new AuthorizationException("revisa que token esté bien creado que te lo he dicho 20 veces");

        }
        catch (RestClientException e) {//aunque la excepción final es java.net.ConnectException, la que se eleva desde postForEntity a nuestro service es RestClientException
            throw new GitMinerNotRunningException("arrancá el GitMiner pelotudo");
        }
    }
}
