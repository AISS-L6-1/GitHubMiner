package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.exceptions.GitMinerNotRunningException;
import aiss.GitHubMiner.exceptions.ProjectNotFoundException;
import aiss.GitHubMiner.services.ProjectService;
import aiss.GitHubMiner.transformers.ProjectDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.net.ConnectException;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    //GET http://localhost:8080/api/projects/{owner}/{repo}
    @GetMapping("/{owner}/{repoName}")
    public ProjectDef findOne(@PathVariable String owner, @PathVariable String repoName, @RequestParam(value = "sinceIssues", defaultValue = "5") Integer sinceIssues, @RequestParam(value = "sinceCommits",defaultValue = "5") Integer sinceCommits, @RequestParam(value = "maxPages",defaultValue = "2") Integer maxPages)
            throws ProjectNotFoundException {
        try {
            ProjectDef project = projectService.getProjectFromOwnerRepo(owner, repoName, sinceIssues, sinceCommits, maxPages);
            return project;
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProjectNotFoundException("Che no lo encontré");
        }
    }
    //POST http://localhost:808X/api/projects/{id} // <-- este post lo tengo que redirigir a GHM o algo así?


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{owner}/{repoName}")
    public ProjectDef create(@PathVariable String owner, @PathVariable String repoName, @RequestParam(value = "sinceIssues", defaultValue = "5") Integer sinceIssues, @RequestParam(value = "sinceCommits",defaultValue = "5") Integer sinceCommits, @RequestParam(value = "maxPages",defaultValue = "2") Integer maxPages)
            throws GitMinerNotRunningException, ProjectNotFoundException {
        try {
            ProjectDef projectDef = projectService.postProjectFromOwnerRepo(owner, repoName, sinceIssues, sinceCommits, maxPages);
            return projectDef;

        } catch (HttpClientErrorException.NotFound e) {
            throw new ProjectNotFoundException("Che no lo encontré");

        } catch (RestClientException e) {
            throw new GitMinerNotRunningException("arrancá el gitMiner pelotudo");
        }
    }
}
