/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.json;

import com.google.common.base.Preconditions;
import com.google.gson.GsonBuilder;

/**
 *
 * @author marlonsouza
 * @param <T>
 */
@FunctionalInterface
public interface ToJson<T> {
    default String go(){
        T entity = fromEntity();
        Preconditions.checkNotNull(entity);
        return new GsonBuilder().create().toJson(entity);
    }
    
    T fromEntity();
}
