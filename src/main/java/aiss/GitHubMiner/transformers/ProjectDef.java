package aiss.GitHubMiner.transformers;

import aiss.GitHubMiner.models.Project;
import aiss.GitHubMiner.services.CommitService;
import aiss.GitHubMiner.services.IssueService;

import java.util.List;

public class ProjectDef {

    private String id;
    private String name;
    private String web_url;
    private List<CommitDef> commits;
    private List<IssueDef> issues;

    //este metodo transforma un proyecto (modelo POJO) en el proyecto (modelo figura 2) al invocarlo se buscan todos
    //  los issues(que se transforman en IssueDef, ver getAllIssues) y todos los Comments del proyecto
    public static ProjectDef transformaProject(Project project, List<CommitDef> commitDefList, List<IssueDef> issueDefList){
        return new ProjectDef(project.getId(), project.getName(), project.getUrl(),commitDefList,issueDefList);
    }

    public ProjectDef(String id, String name, String web_url, List<CommitDef> listCommits, List<IssueDef> listIssue) {
        this.id = id;
        this.name = name;
        this.web_url = web_url;
        this.commits = listCommits;
        this.issues = listIssue;
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

    public List<CommitDef> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitDef> commits) {
        this.commits = commits;
    }

    public List<IssueDef> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueDef> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "ProjectDef{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web_url='" + web_url + '\'' +
                ", Commits=" + commits +
                ", Issues=" + issues +
                '}';
    }
}
