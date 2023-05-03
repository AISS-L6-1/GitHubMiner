package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.services.ProjectService;
import aiss.GitHubMiner.transformers.ProjectDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    //GET http://localhost:8080/api/projects
    // Este método hay que quitarlo porque github no tiene un método para obtener todos los proyectos eh
//    @GetMapping
//    public List<ProjectDef> findAll(@RequestParam("since") Integer since, @RequestParam("sinceIssues") Integer sinceIssues, @RequestParam("sinceCommits") Integer sinceCommits, @RequestParam("maxPages") Integer maxPages){
//        // a partir de un projectId, dame todos los commits y todos los issues (los commits e issues se le añaden en ofRaw)
//        List<ProjectDef> projectList = projectService.getAllProjects(since, sinceIssues, sinceCommits, maxPages);
//        return projectList;
//
//    }

    //GET http://localhost:8080/api/projects/{owner}/{repo}
    @GetMapping("/{owner}/{repoName}")
    public ProjectDef findOne(@PathVariable String owner,@PathVariable String repoName , @RequestParam("sinceIssues") Integer sinceIssues, @RequestParam("sinceCommits") Integer sinceCommits, @RequestParam("maxPages") Integer maxPages) {
        ProjectDef project = projectService.getProjectFromOwnerRepo(owner, repoName, sinceIssues, sinceCommits, maxPages);
        return project;
    }
    //POST http://localhost:808X/api/projects/{id} // <-- este post lo tengo que redirigir a GHM o algo así?

}
