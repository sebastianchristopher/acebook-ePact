import models.Model;
import models.Sql2oModel;
import org.apache.log4j.BasicConfigurator;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import java.util.UUID;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/" + "blog",
                "blog_owner", "sparkforthewin", new PostgresQuirks() {
            {
                // make sure we use default UUID converter.
                converters.put(UUID.class, new UUIDConverter());
            }
        });

        Model model = new Sql2oModel(sql2o);

        if(model.getAllPosts().size() == 0) {
            model.createPost("hello", "world");
        }

        get("/", (req, res) -> "Hello World");


        get("/posts", (req, res) -> {
            res.status(200);
            res.type("application/json");

            return model.getAllPosts();
        });
    }
}
