package models;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;
@Data
public class Comment {
    UUID comment_uuid;
    UUID post_uuid;
    String author;
    String content;
    boolean approved;
    Date submission_date;
}