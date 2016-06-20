/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.repository;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
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
    
    @Autowired
    private MongoOperations mongoOperations;

    private static final String NAME_CLASS = "nameClass";

    public void insert(DocumentDB toPersist) {
        Preconditions.checkNotNull(toPersist);
        
        mongoOperations.save(toPersist);
        
    }

    public void deleteAll(Class clazz) {
        Preconditions.checkNotNull(clazz);
        mongoOperations.remove(new Query(), clazz);
    }

    public List<DocumentDB> listAll() {

        List<DocumentDB> result = Lists.newArrayList();

        mongoOperations.getCollectionNames().forEach(n -> {
            mongoOperations.getCollection(n).find().forEach(i ->{
                final String nameClass = (String) i.get(NAME_CLASS);
                
                Optional.ofNullable(nameClass)
                    .ifPresent((String name) -> {
                        Class<?> clazz = null;

                        try {
                            clazz = Class.forName(name);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MongoRepository.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        FromJson fromJson = (FromJson) ImmutableBindMongo.builder().clazz(clazz).json(i.toString())::build;

                        result.add((DocumentDB) fromJson.go());
                    });
            });
        });
        
        return result;
    }

}
