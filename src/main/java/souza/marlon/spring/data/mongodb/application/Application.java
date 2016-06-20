/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import souza.marlon.spring.data.mongodb.repository.MongoRepository;

/**
 *
 * @author marlonsouza
 */
@ComponentScan(basePackageClasses = {
    MongoRepository.class, Application.class
})
@SpringBootApplication
public class Application {
    
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
    
}
