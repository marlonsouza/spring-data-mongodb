/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

/**
 *
 * @author marlonsouza
 */
@Configuration
@PropertySources(@PropertySource("config.properties"))
public class ApplicationProperties {
    
    @Autowired
    private Environment env;
    
    public String getPropertie(String propName){
        return env.getProperty(propName);
    }
    
}
