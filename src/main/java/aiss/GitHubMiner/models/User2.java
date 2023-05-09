
package aiss.GitHubMiner.models;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "login",
        "id",
        "avatar_url",
        "url",
        "name"
})
@Generated("jsonschema2pojo")
public class User2 {

    public User2(String id, String username, String avatar_url, String url, String name) {
        this.id = id;
        this.login = username;
        this.avatar_url = avatar_url;
        this.web_url = url;
        this.name = name;
    }
    @JsonProperty("id")
    public String id;

    @JsonProperty("login")
    public String login;

    @JsonProperty("avatar_url")
    public String avatar_url;

    @JsonProperty("url")
    public String web_url;

    @JsonProperty("name")
    public String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatar_url = avatarUrl;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDef{" +
                "id=" + id +
                ", username='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", url='" + web_url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
