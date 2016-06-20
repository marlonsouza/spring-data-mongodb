/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.json;

import com.google.common.base.Preconditions;
import com.google.gson.GsonBuilder;
import souza.marlon.spring.data.mongodb.immutable.ImmutableBindMongo;

/**
 *
 * @author marlonsouza
 * @param <T>
 */
@FunctionalInterface
public interface FromJson<T> {
    default T go(){
        ImmutableBindMongo bindMongo = bindMongo();
        Preconditions.checkNotNull(bindMongo.json());
        Preconditions.checkNotNull(bindMongo.clazz());
        
        return (T) new GsonBuilder().create().fromJson(bindMongo.json(), bindMongo.clazz());
    }
    
    ImmutableBindMongo bindMongo();
}
