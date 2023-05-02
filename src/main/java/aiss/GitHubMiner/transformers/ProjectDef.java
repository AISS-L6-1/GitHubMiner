package aiss.GitHubMiner.transformers;

import aiss.GitHubMiner.models.CommitDef;
import aiss.GitHubMiner.models.Project;
import aiss.GitHubMiner.services.CommitService;
import aiss.GitHubMiner.services.IssueService;

import java.util.List;

public class ProjectDef {

    private String id;
    private String name;
    private String web_url;
    private List<CommitDef> listCommits;
    private List<IssueDef> listIssue;

    //este metodo transforma un proyecto (modelo POJO) en el proyecto (modelo figura 2) al invocarlo se buscan todos los issues(que se transforman en IssueDef, ver getAllIssues) y todos los Comments del proyecto
    public static ProjectDef ofRaw(Project project, CommitService commitService, IssueService issueService, Integer sinceIssues, Integer sinceCommits, Integer maxPages){
        return new ProjectDef(project.getId(), project.getName(), project.getUrl(),
                commitService.getAllCommits(project.getUrl(), sinceCommits, maxPages),
                issueService.getAllIssues(project.getUrl(), sinceIssues, maxPages));
    }

    public ProjectDef(String id, String name, String web_url, List<CommitDef> listCommits, List<IssueDef> listIssue) {
        this.id = id;
        this.name = name;
        this.web_url = web_url;
        this.listCommits = listCommits;
        this.listIssue = listIssue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<CommitDef> getListCommits() {
        return listCommits;
    }

    public void setListCommits(List<CommitDef> listCommits) {
        this.listCommits = listCommits;
    }

    public List<IssueDef> getListIssue() {
        return listIssue;
    }

    public void setListIssue(List<IssueDef> listIssue) {
        this.listIssue = listIssue;
    }

    @Override
    public String toString() {
        return "ProjectDef{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web_url='" + web_url + '\'' +
                ", listCommits=" + listCommits +
                ", listIssue=" + listIssue +
                '}';
    }
}
