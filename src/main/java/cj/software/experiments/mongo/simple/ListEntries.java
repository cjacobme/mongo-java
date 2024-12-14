package cj.software.experiments.mongo.simple;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

public class ListEntries {

    private static final Logger LOGGER = LogManager.getFormatterLogger();

    public static void main (String[] args) {
        String connectionString = System.getProperty("mongodb.uri");
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("logs");
            MongoCollection<Document> log4j2Collection = mongoDatabase.getCollection("log4j2");
            FindIterable<Document> iterable = log4j2Collection.find(new Document("contextMap.correlation-id", "3185fec7-0ffc-428d-90a0-662c93c64c5c"));
            try (MongoCursor<Document> cursor = iterable.iterator()) {
                int count = 0;
                while (cursor.hasNext() && count < 3) {
                    Document next = cursor.next();
                    String json = next.toJson();
                    LOGGER.info(json);
                    count++;
                }
            }
        }
    }

}
