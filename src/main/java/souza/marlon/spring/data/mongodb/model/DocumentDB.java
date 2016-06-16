/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.model;

import souza.marlon.spring.data.mongodb.json.FromJson;
import souza.marlon.spring.data.mongodb.json.ToJson;

/**
 *
 * @author marlonsouza
 */
public abstract class DocumentDB {
    
    private final String nameClass = this.getClass().getName();
    
    public final String getNameClass(){
      return this.nameClass;
    }

    public final ToJson toJson(){
        return (ToJson) () -> DocumentDB.this;
    };
    
    protected abstract FromJson fromJson(String json);
    
    
}
