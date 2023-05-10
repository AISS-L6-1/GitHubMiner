package aiss.GitHubMiner.services;

import aiss.GitHubMiner.models.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Test de getAllComments")
    void getAllComments() {
        List<Comment> comments = commentService.getAllComments("spring-projects", "spring-framework", 4, 2);
        assertFalse(comments.isEmpty(), "The list of comments is empty.");
        System.out.println(comments);
    }

    /* Hay que ver el issues porque la url que da no es valida
    @Test
    @DisplayName("Test de getAllCommentsFromIssue")
    void getAllCommentsFromIssue() {
        List<Comment> comments = commentService.getAllCommentsFromIssue("https://api.github.com/repos/spring-projects/spring-framework/issues/comments/1540742539");
        assertFalse(comments.isEmpty(), "The list of comments is empty.");
        System.out.println(comments);
    }
     */
}