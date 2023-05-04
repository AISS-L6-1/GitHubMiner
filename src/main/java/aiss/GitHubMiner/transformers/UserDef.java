
package aiss.GitHubMiner.transformers;

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
public class UserDef {

    public UserDef(Integer id, String username, String avatar_url, String url, String name) {
        this.id = id;
        this.username = username;
        this.avatar_url = avatar_url;
        this.url = url;
        this.name = name;
    }
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("login")
    public String username;

   @JsonProperty("avatar_url")
    public String avatar_url;

    @JsonProperty("url")
    public String url;

    @JsonProperty("name")
    public String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatar_url = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                ", username='" + username + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
