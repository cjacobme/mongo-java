package cj.software.experiments.mongo.simple;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

public class ReadFirstLog {

    private static final Logger LOGGER = LogManager.getFormatterLogger();

    public static void main (String[] args) {
        String connectionString = System.getProperty("mongodb.uri");
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("logs");
            MongoCollection<Document> log4j2 = mongoDatabase.getCollection("log4j2");
            Document entry = log4j2.find(new Document("millis", 1734180740511L)).first();
            assert entry != null;
            String json = entry.toJson();
            LOGGER.info(json);
        }
    }

}
