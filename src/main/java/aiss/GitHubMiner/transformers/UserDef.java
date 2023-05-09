package aiss.GitHubMiner.transformers;

import aiss.GitHubMiner.models.User;

public class UserDef {

    private String id;
    private String username;
    private String name;
    private String avatar_url;
    private String web_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public static UserDef transformaUser(User user, String name){
        return new UserDef(user.getId(), user.getLogin(), name, user.getAvatar_url(), user.getUrl());
    }
    public UserDef(String id, String username, String name, String avatar_url, String web_url) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.avatar_url = avatar_url;
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        return "UserDef{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", web_url='" + web_url + '\'' +
                '}';
    }
}
