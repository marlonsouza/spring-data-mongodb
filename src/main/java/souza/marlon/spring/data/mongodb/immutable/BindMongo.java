/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.immutable;

import org.immutables.value.Value;

/**
 *
 * @author marlonsouza
 */
@Value.Immutable
public interface BindMongo {
    String json();
    Class<?> clazz();
}
