
package aiss.GitHubMiner.models;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url",
    "repository_url",
    "labels_url",
    "comments_url",
    "events_url",
    "html_url",
    "id",
    "node_id",
    "number",
    "title",
    "user",
    "labels",
    "state",
    "locked",
    "assignee",
    "assignees",
    "milestone",
    "comments",
    "created_at",
    "updated_at",
    "closed_at",
    "author_association",
    "active_lock_reason",
    "body",
    "reactions",
    "timeline_url",
    "performed_via_github_app",
    "state_reason"
})
@Generated("jsonschema2pojo")
public class Issue {
    @JsonProperty("id")//1 id
    public String id;
    @JsonProperty("node_id")//2 ref_id?
    public String nodeId;
    @JsonProperty("title")//3 title
    public String title;

    @JsonProperty("comments_url")//3 title
    public String comments_url;
    @JsonProperty("comments")//numero de comentarios, si es 0 no buscaremos los comentarios
    public Integer comments;
    @JsonProperty("number")//hace falta para sacarle los comentarios
    public Integer number;
    @JsonProperty("user")//aunque se llame user es el autor del issue
    public User author;
    @JsonProperty("labels")//9 tengo que sacar de la lista de labels el nombre de cada una y con eso tengo la lista de strings
    public List<Label> labels;
    @JsonProperty("state") //5 state
    public String state;
    @JsonProperty("assignee")// no he encontrado ninguno que no sea nullo pero debe ser un user
    public User assignee;
    @JsonProperty("created_at")//6 created_at
    public String createdAt;
    @JsonProperty("updated_at")//7 updated_at
    public String updatedAt;
    @JsonProperty("closed_at")//8 closed_at
    public Object closedAt;
    @JsonProperty("body")//4 description
    public String body;
    @JsonProperty("reactions")//upvotes / downvotes
    public Reactions reactions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Reactions getReactions() {
        return reactions;
    }

    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", title='" + title + '\'' +
                ", comments_url='" + comments_url + '\'' +
                ", comments=" + comments +
                ", number=" + number +
                ", author=" + author +
                ", labels=" + labels +
                ", state='" + state + '\'' +
                ", assignee=" + assignee +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt=" + closedAt +
                ", body='" + body + '\'' +
                ", reactions=" + reactions +
                '}';
    }
}
