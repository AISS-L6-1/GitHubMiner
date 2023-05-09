
package aiss.GitHubMiner.models;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "username",
    "avatar_url",
    "url"
})
@Generated("jsonschema2pojo")
public class User {

    @JsonProperty("login")
    private String login;
    @JsonProperty("id")
    private String id;

    @JsonProperty("avatar_url")
    private String avatar_url;
    @JsonProperty("url")
    private String url;

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("avatar_url")
    public String getAvatar_url() {
        return avatar_url;
    }

    @JsonProperty("avatar_url")
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public User(String username, String id, String avatar_url, String url) {
        this.login = username;
        this.id = id;
        this.avatar_url = avatar_url;
        this.url = url;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
