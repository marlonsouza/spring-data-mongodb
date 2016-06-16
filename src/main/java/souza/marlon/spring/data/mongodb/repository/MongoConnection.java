/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author marlonsouza
 */
public class MongoConnection {
    private static final String DATABASE_NAME = "wonderfulDB";
    
    private static MongoDatabase myMongo;
    
    public static MongoDatabase getConnection(){
        return Optional.ofNullable(myMongo)
            .map(Function.identity())
            .orElse(newConnection());
    }

    private static MongoDatabase newConnection() {
        MongoClient mongoClient = new MongoClient("localhost", 27020);
        myMongo = mongoClient.getDatabase(DATABASE_NAME);
        return myMongo;
    }
}
