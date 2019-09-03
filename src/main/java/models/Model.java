package models;


import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface Model {
    UUID createPost(String title, String content);
    List getAllPosts();
    boolean existPost(UUID post);
}


