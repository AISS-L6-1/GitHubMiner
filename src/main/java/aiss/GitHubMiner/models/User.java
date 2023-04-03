
package aiss.GitHubMiner.models;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "login",
    "id",
    "node_id",
    "avatar_url",
    "gravatar_id",
    "url",
    "html_url",
    "followers_url",
    "following_url",
    "gists_url",
    "starred_url",
    "subscriptions_url",
    "organizations_url",
    "repos_url",
    "events_url",
    "received_events_url",
    "type",
    "site_admin"
})
@Generated("jsonschema2pojo")
public class User {

    @JsonProperty("login")
    private String login;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("node_id")
    private String node_id;
    @JsonProperty("avatar_url")
    private String avatar_url;
    @JsonProperty("gravatar_id")
    private String gravatar_id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String html_url;
    @JsonProperty("followers_url")
    private String followers_url;
    @JsonProperty("following_url")
    private String following_url;
    @JsonProperty("gists_url")
    private String gists_url;
    @JsonProperty("starred_url")
    private String starred_url;
    @JsonProperty("subscriptions_url")
    private String subscriptions_url;
    @JsonProperty("organizations_url")
    private String organizations_url;
    @JsonProperty("repos_url")
    private String repos_url;
    @JsonProperty("events_url")
    private String events_url;
    @JsonProperty("received_events_url")
    private String received_events_url;
    @JsonProperty("type")
    private String type;
    @JsonProperty("site_admin")
    private Boolean site_admin;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("node_id")
    public String getNode_id() {
        return node_id;
    }

    @JsonProperty("node_id")
    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    @JsonProperty("avatar_url")
    public String getAvatar_url() {
        return avatar_url;
    }

    @JsonProperty("avatar_url")
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @JsonProperty("gravatar_id")
    public String getGravatar_id() {
        return gravatar_id;
    }

    @JsonProperty("gravatar_id")
    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("html_url")
    public String getHtml_url() {
        return html_url;
    }

    @JsonProperty("html_url")
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @JsonProperty("followers_url")
    public String getFollowers_url() {
        return followers_url;
    }

    @JsonProperty("followers_url")
    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    @JsonProperty("following_url")
    public String getFollowing_url() {
        return following_url;
    }

    @JsonProperty("following_url")
    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    @JsonProperty("gists_url")
    public String getGists_url() {
        return gists_url;
    }

    @JsonProperty("gists_url")
    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    @JsonProperty("starred_url")
    public String getStarred_url() {
        return starred_url;
    }

    @JsonProperty("starred_url")
    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    @JsonProperty("subscriptions_url")
    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    @JsonProperty("subscriptions_url")
    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    @JsonProperty("organizations_url")
    public String getOrganizations_url() {
        return organizations_url;
    }

    @JsonProperty("organizations_url")
    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    @JsonProperty("repos_url")
    public String getRepos_url() {
        return repos_url;
    }

    @JsonProperty("repos_url")
    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    @JsonProperty("events_url")
    public String getEvents_url() {
        return events_url;
    }

    @JsonProperty("events_url")
    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    @JsonProperty("received_events_url")
    public String getReceived_events_url() {
        return received_events_url;
    }

    @JsonProperty("received_events_url")
    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("site_admin")
    public Boolean getSite_admin() {
        return site_admin;
    }

    @JsonProperty("site_admin")
    public void setSite_admin(Boolean site_admin) {
        this.site_admin = site_admin;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("login");
        sb.append('=');
        sb.append(((this.login == null)?"<null>":this.login));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("node_id");
        sb.append('=');
        sb.append(((this.node_id == null)?"<null>":this.node_id));
        sb.append(',');
        sb.append("avatar_url");
        sb.append('=');
        sb.append(((this.avatar_url == null)?"<null>":this.avatar_url+ " "));
        sb.append(',');
        sb.append("gravatar_id");
        sb.append('=');
        sb.append(((this.gravatar_id == null)?"<null>":this.gravatar_id+" "));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url+" "));
        sb.append(',');
        sb.append("html_url");
        sb.append('=');
        sb.append(((this.html_url == null)?"<null>":this.html_url+" "));
        sb.append(',');
        sb.append("followers_url");
        sb.append('=');
        sb.append(((this.followers_url == null)?"<null>":this.followers_url+" "));
        sb.append(',');
        sb.append("following_url");
        sb.append('=');
        sb.append(((this.following_url == null)?"<null>":this.following_url+" "));
        sb.append(',');
        sb.append("gists_url");
        sb.append('=');
        sb.append(((this.gists_url == null)?"<null>":this.gists_url+" "));
        sb.append(',');
        sb.append("starred_url");
        sb.append('=');
        sb.append(((this.starred_url == null)?"<null>":this.starred_url+" "));
        sb.append(',');
        sb.append("subscriptions_url");
        sb.append('=');
        sb.append(((this.subscriptions_url == null)?"<null>":this.subscriptions_url+" "));
        sb.append(',');
        sb.append("organizations_url");
        sb.append('=');
        sb.append(((this.organizations_url == null)?"<null>":this.organizations_url+" "));
        sb.append(',');
        sb.append("repos_url");
        sb.append('=');
        sb.append(((this.repos_url == null)?"<null>":this.repos_url+" "));
        sb.append(',');
        sb.append("events_url");
        sb.append('=');
        sb.append(((this.events_url == null)?"<null>":this.events_url+" "));
        sb.append(',');
        sb.append("received_events_url");
        sb.append('=');
        sb.append(((this.received_events_url == null)?"<null>":this.received_events_url+" "));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("site_admin");
        sb.append('=');
        sb.append(((this.site_admin == null)?"<null>":this.site_admin));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
