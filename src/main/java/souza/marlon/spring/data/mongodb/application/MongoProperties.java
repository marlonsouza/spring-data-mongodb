/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.application;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marlonsouza
 */
@Component
public class MongoProperties {
    
    @Autowired
    private ApplicationProperties applicationProperties;
    
    private String host;
    private Integer port;
    
    @PostConstruct
    private void init(){
        this.host = applicationProperties.getPropertie(FieldPropertie.HOST.field());
        this.port = Integer.parseInt(applicationProperties.getPropertie(FieldPropertie.PORT.field()));
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }
    
    private enum FieldPropertie{
        
        HOST("mongodb.host"),
        PORT("mongodb.port");
        
        private final String field;

        private FieldPropertie(String field) {
            this.field = field;
        }
        
        private String field(){
            return this.field;
        }
        
    }
    
}
