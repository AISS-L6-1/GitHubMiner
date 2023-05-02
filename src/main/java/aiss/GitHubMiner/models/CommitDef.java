package aiss.GitHubMiner.models;

import java.time.LocalDate;
import java.util.Date;

public class CommitDef {
    private String id;
    private String tittle; // <-- como no existe vamos a coger la primera frase del message?
    private String message;
    private String authorName;
    private String authorEmail;
    private LocalDate authoredDate;
    private String commiterName;
    private String commiterEmail;
    private String commitedDate;
    private String url;

    public CommitDef(String id, String tittle, String message, String authorName, String authorEmail, LocalDate authoredDate, String commiterName, String commiterEmail, String commitedDate, String url) {
        this.id = id;
        this.tittle = tittle;
        this.message = message;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authoredDate = authoredDate;
        this.commiterName = commiterName;
        this.commiterEmail = commiterEmail;
        this.commitedDate = commitedDate;
        this.url = url;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public LocalDate getAuthoredDate() {
        return authoredDate;
    }

    public void setAuthoredDate(LocalDate authoredDate) {
        this.authoredDate = authoredDate;
    }

    public String getCommiterName() {
        return commiterName;
    }

    public void setCommiterName(String commiterName) {
        this.commiterName = commiterName;
    }

    public String getCommiterEmail() {
        return commiterEmail;
    }

    public void setCommiterEmail(String commiterEmail) {
        this.commiterEmail = commiterEmail;
    }

    public String getCommitedDate() {
        return commitedDate;
    }

    public void setCommitedDate(String commitedDate) {
        this.commitedDate = commitedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Commit {" +
                "id='" + id + '\'' +
                ", tittle='" + tittle + '\'' +
                ", message='" + message + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", authoredDate='" + authoredDate + '\'' +
                ", commiterName='" + commiterName + '\'' +
                ", commiterEmail='" + commiterEmail + '\'' +
                ", commitedDate='" + commitedDate + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
