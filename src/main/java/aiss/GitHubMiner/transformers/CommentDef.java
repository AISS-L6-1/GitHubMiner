package aiss.GitHubMiner.transformers;

import aiss.GitHubMiner.models.Comment;
import aiss.GitHubMiner.services.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentDef {
    public Integer id;
    public UserDef author;
    public String createdAt;
    public String updatedAt;
    public String body;


    public static CommentDef ofRaw(Comment comment, UserService userService) {
        return new CommentDef(comment.getId(), userService.getUser(comment.getAuthor().getUsername()), comment.getCreatedAt(), comment.getUpdatedAt(), comment.getBody());
    }

    public CommentDef(Integer id, UserDef author, String createdAt, String updatedAt, String body) {
        this.id = id;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommentDef{" +
                "id=" + id +
                ", author=" + author +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDef getAuthor() {
        return author;
    }

    public void setAuthor(UserDef author) {
        this.author = author;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
