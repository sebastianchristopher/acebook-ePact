import models.Model;
import models.Sql2oModel;
import org.apache.log4j.BasicConfigurator;
import org.flywaydb.core.Flyway;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import java.util.UUID;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/acebook", null, null).load();
        flyway.migrate();

        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/" + "acebook", null, null, new PostgresQuirks() {
            {
                // make sure we use default UUID converter.
                converters.put(UUID.class, new UUIDConverter());
            }
        });

        Model model = new Sql2oModel(sql2o);


        get("/", (req, res) -> "Hello World");


        get("/posts", (req, res) -> {
            System.out.println("***********************");
            System.out.println(model.getAllPosts().size());
            if(model.getAllPosts().size() == 0) {
                UUID id = model.createPost("hello", "world");
                System.out.println("************ ID ******* " + id);
            }

            res.status(200);
            res.type("application/json");

            return model.getAllPosts();
        });
    }
}
