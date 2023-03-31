
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
    "state_reason",
    "draft",
    "pull_request"
})
@Generated("jsonschema2pojo")
public class Issue {

    @JsonProperty("url")
    public String url;
    @JsonProperty("repository_url")
    public String repositoryUrl;
    @JsonProperty("labels_url")
    public String labelsUrl;
    @JsonProperty("comments_url")
    public String commentsUrl;
    @JsonProperty("events_url")
    public String eventsUrl;
    @JsonProperty("html_url")
    public String htmlUrl;
    @JsonProperty("id")//1 id
    public Integer id;
    @JsonProperty("node_id")//2 ref_id?
    public String nodeId;
    @JsonProperty("number")
    public Integer number;
    @JsonProperty("title")//3 title
    public String title;
    @JsonProperty("user")
    public User user;
    @JsonProperty("labels")//9 tengo que sacar de la lista de labels el nombre de cada una y con eso tengo la lista de strings
    public List<Label> labels;
    @JsonProperty("state") //5 state
    public String state;
    @JsonProperty("locked")
    public Boolean locked;
    @JsonProperty("assignee")
    public Object assignee;
    @JsonProperty("milestone")
    public Object milestone;
    @JsonProperty("comments")
    public Integer comments;
    @JsonProperty("created_at")//6 created_at
    public String createdAt;
    @JsonProperty("updated_at")//7 updated_at
    public String updatedAt;
    @JsonProperty("closed_at")//8 closed_at
    public Object closedAt;
    @JsonProperty("author_association")
    public String authorAssociation;
    @JsonProperty("active_lock_reason")
    public Object activeLockReason;
    @JsonProperty("body")//4 description
    public String body;
    @JsonProperty("reactions")//upvotes / downvotes
    public Reactions reactions;
    @JsonProperty("timeline_url")
    public String timelineUrl;
    @JsonProperty("performed_via_github_app")
    public Object performedViaGithubApp;
    @JsonProperty("state_reason")
    public Object stateReason;
    @JsonProperty("draft")
    public Boolean draft;
    @JsonProperty("pull_request")
    public PullRequest pullRequest;

}
