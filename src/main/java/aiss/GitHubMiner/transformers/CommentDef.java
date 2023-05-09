package aiss.GitHubMiner.transformers;

import aiss.GitHubMiner.models.Comment;
import aiss.GitHubMiner.models.User2;
import aiss.GitHubMiner.services.UserService;

public class CommentDef {
    public Integer id;
    public UserDef author;
    public String created_at;
    public String updated_at;
    public String body;


    public static CommentDef transformaComment(Comment comment, UserDef userDef) {
        return new CommentDef(comment.getId(), userDef, comment.getCreatedAt(), comment.getUpdatedAt(), comment.getBody());
    }

    public CommentDef(Integer id, UserDef author, String createdAt, String updatedAt, String body) {
        this.id = id;
        this.author = author;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommentDef{" +
                "id=" + id +
                ", author=" + author +
                ", createdAt='" + created_at + '\'' +
                ", updatedAt='" + updated_at + '\'' +
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
