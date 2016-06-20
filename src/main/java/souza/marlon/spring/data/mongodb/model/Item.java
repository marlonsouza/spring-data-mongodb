/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.model;

import java.math.BigInteger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author marlonsouza
 */
@Document
public class Item {
    
    @Id
    private BigInteger id;
    
    private String descricao;

    public BigInteger getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
