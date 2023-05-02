package aiss.GitHubMiner.transformers;

import aiss.GitHubMiner.models.Comment;
import aiss.GitHubMiner.models.Issue;
import aiss.GitHubMiner.models.User;
import aiss.GitHubMiner.services.CommentService;

import java.util.ArrayList;
import java.util.List;

public class IssueDef {

    private String id;
    private String ref_id;
    private String title;
    private String description;
    private String state;
    private String createdAt;
    private String updatedAt;
    private Object closedAt;
    private List<String> labels;
    private Integer upvotes;
    private Integer downvotes;
    private User author;
    private User assignee;

    private List<Comment> listComments;


    //metodo que usaremos para unir a nuestro Issue la lista de Comments que obtenemos con getCommentsFromId. Luego se hará una lista de IssueDef que será una propiedad de ProjectDef
    public static IssueDef ofRaw(Issue issueRaw, CommentService commentService, Integer sinceDays, Integer maxPages){
        List<Comment> commentList = new ArrayList<>();
        return new IssueDef(issueRaw.getId(), issueRaw.getNodeId(), issueRaw.getTitle(), issueRaw.getBody(),
                issueRaw.getState(), issueRaw.getCreatedAt(), issueRaw.getUpdatedAt(), issueRaw.getClosedAt(), extractLabels(issueRaw),
                extractUpvotes(issueRaw), extractDownvotes(issueRaw), issueRaw.getAuthor(), issueRaw.getAssignee(),
                issueRaw.getComments() == 0 ? commentList: commentService.getAllCommentsFromIssue(issueRaw.getComments_url()));
    }

    private static Integer extractDownvotes(Issue issueRaw) {
        return issueRaw.reactions.getDownvotes();
    }

    private static Integer extractUpvotes(Issue issueRaw) {
        return issueRaw.reactions.getUpvotes();
    }

    private static List<String> extractLabels(Issue issueRaw) {
        return issueRaw.getLabels().stream().map(l -> l.getName()).toList();
    }

    public IssueDef(String id, String ref_id, String title, String description, String state, String createdAt, String updatedAt, Object closedAt, List<String> labels, Integer upvotes, Integer downvotes, User author, User assignee, List<Comment> listComments) {
        this.id = id;
        this.ref_id = ref_id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
        this.labels = labels;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.author = author;
        this.assignee = assignee;
        this.listComments = listComments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public List<Comment> getListComments() {
        return listComments;
    }

    public void setListComments(List<Comment> listComments) {
        this.listComments = listComments;
    }

    @Override
    public String toString() {
        return "IssueDef{" +
                "id='" + id + '\'' +
                ", ref_id='" + ref_id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt=" + closedAt +
                ", labels=" + labels +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", author=" + author +
                ", assignee=" + assignee +
                ", listComments=" + listComments +
                '}';
    }
}
