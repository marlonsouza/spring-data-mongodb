/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.application;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 *
 * @author marlonsouza
 */
@Configuration
public class ApplicationConfiguration {
    private final String DATABASE_NAME = "wonderfulDB";
    
    @Autowired
    private MongoProperties mongoProperties;
    
    @Bean
    public MongoDbFactory mongoDbFactory(){
        try {
            return new SimpleMongoDbFactory(new MongoClient(mongoProperties.getHost(), mongoProperties.getPort()), DATABASE_NAME);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ApplicationConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Bean
    public MongoOperations mongoOperations(){
        return new MongoTemplate(mongoDbFactory());
    }
}
