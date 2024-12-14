package cj.software.experiments.mongo.simple;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Connection {

    private static final Logger LOGGER = LogManager.getFormatterLogger();

    public static void main (String[] args) {
        String connectionString = System.getProperty("mongodb.uri");
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            LOGGER.info("client connected");
            LOGGER.info("theses are the databases I found:");
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            for (Document database : databases) {
                String asJson = database.toJson();
                LOGGER.info(asJson);
            }
        }
    }
}
