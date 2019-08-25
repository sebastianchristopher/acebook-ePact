package models;


import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface Model {
    UUID createPost(String title, String content);
    UUID createComment(UUID post, String author, String content);
    List getAllPosts();
    List getAllCommentsOn(UUID post);
    boolean existPost(UUID post);
}


