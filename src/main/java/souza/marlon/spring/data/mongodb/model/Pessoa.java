/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import souza.marlon.spring.data.mongodb.immutable.ImmutableBindMongo;
import souza.marlon.spring.data.mongodb.json.FromJson;

/**
 *
 * @author marlonsouza
 */
@Document
public class Pessoa extends DocumentDB{
    
    @Id
    private Long id;
    
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    protected FromJson fromJson(String json) {
        return ImmutableBindMongo.builder().clazz(Pessoa.class).json(json)::build;
    }
    
}
