
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
    "id",
    "username",
    "name",
    "avatar_url",
    "url"
})
@Generated("jsonschema2pojo")
public class User {

    @JsonProperty("login")
    private String username;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("avatar_url")
    private String avatar_url;
    @JsonProperty("url")
    private String url;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @JsonProperty("login")
    public String getUsername() {
        return username;
    }

    @JsonProperty("login")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
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

    public String getName() {
        return this.name;
    }

    public User(String username, Integer id, String avatar_url, String url, String name) {
        this.username = username;
        this.id = id;
        this.avatar_url = avatar_url;
        this.url = url;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
