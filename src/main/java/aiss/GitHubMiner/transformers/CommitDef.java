package aiss.GitHubMiner.transformers;

import aiss.GitHubMiner.models.Commit;

import java.time.LocalDate;

import static aiss.GitHubMiner.utils.funciones.*;

public class CommitDef {
    private String id;
    private String tittle; // <-- como no existe vamos a coger la primera frase del message?
    private String message;
    private String author_name;
    private String author_email;
    private LocalDate authored_date;
    private String committer_name;
    private String committer_email;
    private String committed_date;
    private String web_url;

    public static CommitDef transformaCommit(Commit commit) {
        String id = commit.getSha();
        String message = commit.getCommit().getMessage();
        String tittle = titleFromMessage(message);
        String authorName = commit.getCommit().getAuthor().getName();
        String authorEmail = commit.getCommit().getAuthor().getEmail();
        LocalDate authoredDate = dateParse(commit.getCommit().getAuthor().getDate());
        String committerName = commit.getCommit().getCommitter().getName();
        String committerEmail = commit.getCommit().getCommitter().getEmail();
        String committedDate = commit.getCommit().getCommitter().getDate();
        String url = commit.getUrl();
        return new CommitDef(id, tittle, message, authorName, authorEmail, authoredDate, committerName, committerEmail, committedDate, url);
    }

    public CommitDef(String id, String tittle, String message, String authorName, String authorEmail, LocalDate authoredDate, String commiterName, String commiterEmail, String commitedDate, String url) {
        this.id = id;
        this.tittle = tittle;
        this.message = message;
        this.author_name = authorName;
        this.author_email = authorEmail;
        this.authored_date = authoredDate;
        this.committer_name = commiterName;
        this.committer_email = commiterEmail;
        this.committed_date = commitedDate;
        this.web_url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public LocalDate getAuthored_date() {
        return authored_date;
    }

    public void setAuthored_date(LocalDate authored_date) {
        this.authored_date = authored_date;
    }

    public String getCommitter_name() {
        return committer_name;
    }

    public void setCommitter_name(String committer_name) {
        this.committer_name = committer_name;
    }

    public String getCommitter_email() {
        return committer_email;
    }

    public void setCommitter_email(String committer_email) {
        this.committer_email = committer_email;
    }

    public String getCommitted_date() {
        return committed_date;
    }

    public void setCommitted_date(String committed_date) {
        this.committed_date = committed_date;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        return "CommitDef{" +
                "id='" + id + '\'' +
                ", tittle='" + tittle + '\'' +
                ", message='" + message + '\'' +
                ", author_name='" + author_name + '\'' +
                ", author_email='" + author_email + '\'' +
                ", authored_date=" + authored_date +
                ", committer_name='" + committer_name + '\'' +
                ", committer_email='" + committer_email + '\'' +
                ", committed_date='" + committed_date + '\'' +
                ", web_url='" + web_url + '\'' +
                '}';
    }
}
