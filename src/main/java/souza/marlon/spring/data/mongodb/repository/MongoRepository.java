/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.repository;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.springframework.stereotype.Component;
import souza.marlon.spring.data.mongodb.immutable.ImmutableBindMongo;
import souza.marlon.spring.data.mongodb.json.FromJson;
import souza.marlon.spring.data.mongodb.model.DocumentDB;

/**
 *
 * @author marlonsouza
 */
@Component
public class MongoRepository {

    private static final String NAME_CLASS = "nameClass";

    public void insert(DocumentDB toPersist) {

        MongoDatabase mongoDatabase = MongoConnection.getConnection();
        final String simpleName = toPersist.getClass().getSimpleName();

        if (mongoDatabase.getCollection(simpleName) == null) {
            mongoDatabase.createCollection(simpleName);
        }

        Document jsonToInsert = Document.parse(toPersist.toJson().go());

        mongoDatabase.getCollection(simpleName).insertOne(jsonToInsert);

    }

    public void deleteAll(Class clazz) {
        Preconditions.checkNotNull(clazz);
        MongoDatabase mongoDatabase = MongoConnection.getConnection();
        final String simpleName = clazz.getSimpleName();
        mongoDatabase.getCollection(simpleName).drop();
    }

    public List<DocumentDB> listAll() {
        MongoDatabase mongoDatabase = MongoConnection.getConnection();

        List<DocumentDB> result = Lists.newArrayList();

        mongoDatabase.listCollectionNames().forEach((Consumer<String>) n -> {
            mongoDatabase.getCollection(n).find().forEach((Consumer<Document>) (Document i) -> {
                final String nameClass = i.getString(NAME_CLASS);

                Optional.ofNullable(nameClass)
                    .ifPresent((String name) -> {
                        Class<?> clazz = null;

                        try {
                            clazz = Class.forName(name);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MongoRepository.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        FromJson fromJson = (FromJson) ImmutableBindMongo.builder().clazz(clazz).json(i.toJson())::build;

                        result.add((DocumentDB) fromJson.go());
                    });
            });
        });

        return result;
    }

}
