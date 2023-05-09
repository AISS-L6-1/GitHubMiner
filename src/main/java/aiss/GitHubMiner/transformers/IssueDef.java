package aiss.GitHubMiner.transformers;

import aiss.GitHubMiner.models.Issue;

import java.util.List;

public class IssueDef {

    private String id;
    private String ref_id;
    private String title;
    private String description;
    private String state;
    private String created_at;
    private String updated_at;
    private Object closed_at;
    private List<String> labels;
    private Integer upvotes;
    private Integer downvotes;
    private UserDef author;
    private UserDef assignee;

    private List<CommentDef> comments;


    //metodo que usaremos para unir a nuestro Issue la lista de Comments que obtenemos con getCommentsFromId. Luego se hará una lista de IssueDef que será una propiedad de ProjectDef
    public static IssueDef transformaIssue(Issue issue, List<CommentDef> listCommentsDef, UserDef author, UserDef assignee){
        return new IssueDef(issue.getId(), issue.getNodeId(), issue.getTitle(), issue.getBody(),
                issue.getState(), issue.getCreatedAt(), issue.getUpdatedAt(), issue.getClosedAt(), extractLabels(issue),
                extractUpvotes(issue), extractDownvotes(issue), author, assignee, listCommentsDef);
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

    public IssueDef(String id, String ref_id, String title, String description, String state, String createdAt, String updatedAt, Object closedAt, List<String> labels, Integer upvotes, Integer downvotes, UserDef author, UserDef assignee, List<CommentDef> listComments) {
        this.id = id;
        this.ref_id = ref_id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
        this.closed_at = closedAt;
        this.labels = labels;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.author = author;
        this.assignee = assignee;
        this.comments = listComments;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(Object closed_at) {
        this.closed_at = closed_at;
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

    public UserDef getAuthor() {
        return author;
    }

    public void setAuthor(UserDef author) {
        this.author = author;
    }

    public UserDef getAssignee() {
        return assignee;
    }

    public void setAssignee(UserDef assignee) {
        this.assignee = assignee;
    }

    public List<CommentDef> getComments() {
        return comments;
    }

    public void setComments(List<CommentDef> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "IssueDef{" +
                "id='" + id + '\'' +
                ", ref_id='" + ref_id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createdAt='" + created_at + '\'' +
                ", updatedAt='" + updated_at + '\'' +
                ", closedAt=" + closed_at +
                ", labels=" + labels +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", author=" + author +
                ", assignee=" + assignee +
                ", listComments=" + comments +
                '}';
    }
}
